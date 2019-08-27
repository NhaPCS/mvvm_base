package com.nhapt.base.util;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.nhapt.base.R;
import com.nhapt.base.listener.OnCaptureVideoListener;
import com.nhapt.base.listener.OnSelectImageListener;
import com.nhapt.base.mvvm.MVVMFragment;

import java.io.File;

import static android.app.Activity.RESULT_OK;
import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;

public class TakeImageManager {
    private static final int PERMISSION_CAMERA = 100;
    private static final int OPEN_CAMERA = 201;
    private static final int OPEN_VIDEO_CAPTURE = 303;
    private static final int SETTING_CAMERA = 302;

    private String mImageFile;
    private Uri mVideoUri;
    private MVVMFragment mFragment;
    private OnSelectImageListener mOnGetImageListener;
    private OnCaptureVideoListener mOnCaptureVideoListener;

    public TakeImageManager(MVVMFragment mFragment) {
        this.mFragment = mFragment;
    }

    public void setmOnGetImageListener(OnSelectImageListener mOnGetImageListener) {
        this.mOnGetImageListener = mOnGetImageListener;
    }

    public void setmOnCaptureVideoListener(OnCaptureVideoListener mOnCaptureVideoListener) {
        this.mOnCaptureVideoListener = mOnCaptureVideoListener;
    }

    public void onTakeImage() {
        if (ContextCompat.checkSelfPermission(mFragment.getBaseContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(mFragment.getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (!mFragment.shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                mFragment.requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSION_CAMERA);
            } else {
                notShouldShowCameraPermission();
            }

        } else {
            onCaptureImage();
        }
    }

    public void onTakeVideo() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(mFragment.getBaseContext().getPackageManager()) != null) {
            mFragment.startActivityForResult(takeVideoIntent, OPEN_VIDEO_CAPTURE);
        }
    }


    private void onCaptureImage() {
        try {
            if (ContextCompat.checkSelfPermission(mFragment.getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                File photoDirectory = mFragment.getBaseContext().getExternalFilesDir("photo");
                if (!photoDirectory.exists() && !photoDirectory.mkdirs()) {
                    return;
                }
                File photo = new File(photoDirectory, System.currentTimeMillis() + ".jpg");
                mImageFile = photo.getAbsolutePath();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri mPhotoUri = FileProvider.getUriForFile(mFragment.getBaseContext(), mFragment.getBaseContext().getPackageName() + ".provider", photo);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoUri);
                intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION);
                mFragment.startActivityForResult(intent, OPEN_CAMERA);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void notShouldShowCameraPermission() {
        mFragment.showConfirmDialog(mFragment.getString(R.string.notice),
                mFragment.getString(R.string.mess_allow_camera_permission),
                mFragment.getString(R.string.ok), mFragment.getString(R.string.cancel), () -> {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", mFragment.getApplication().getPackageName(), null);
                    intent.setData(uri);
                    mFragment.startActivityForResult(intent, SETTING_CAMERA);
                });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (resultCode == RESULT_OK) {
                switch (requestCode) {
                    case OPEN_CAMERA:
                        resizeImage();
                        break;
                    case OPEN_VIDEO_CAPTURE:
                        mVideoUri = data.getData();
                        if (mOnCaptureVideoListener != null)
                            mOnCaptureVideoListener.onCaptured(mVideoUri);
                        break;
                }
                return;
            }
            if (requestCode == SETTING_CAMERA) {
                if (ContextCompat.checkSelfPermission(mFragment.getBaseContext(), Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_GRANTED) {
                    onCaptureImage();
                }
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        try {
            switch (requestCode) {
                case PERMISSION_CAMERA:
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        onCaptureImage();
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resizeImage() {
        try {
            if (mImageFile == null) return;
            mImageFile = FileUtil.resizeImageFile(mImageFile, true);
            if (mOnGetImageListener != null) {
                mOnGetImageListener.imageSelected(mImageFile);
                Log.e("imageSelected", mImageFile + "");
            }

        } catch (Exception e) {
            e.printStackTrace();
            mImageFile = null;
        }
    }
}
