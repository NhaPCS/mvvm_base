package com.nhapt.base.mvvm;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nhapt.base.dialog.ConfirmDialog;
import com.nhapt.base.dialog.LoadingDialog;
import com.nhapt.base.dialog.MessageDialog;
import com.nhapt.base.listener.OnOkClickListener;

public abstract class MVVMActivity extends AppCompatActivity {
    private LoadingDialog mLoadingDialog;
    private MessageDialog mMessageDialog;
    private ConfirmDialog mConfirmDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadingDialog = new LoadingDialog();
        mMessageDialog = new MessageDialog();
        mConfirmDialog = new ConfirmDialog();
    }

    public void showLoading() {
        try {
            if (mLoadingDialog == null) {
                mLoadingDialog = new LoadingDialog();
            }
            if (!mLoadingDialog.isAdded()) {
                mLoadingDialog.show(getSupportFragmentManager(), mLoadingDialog.getClass().getSimpleName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideLoading() {
        try {
            if (mLoadingDialog != null && mLoadingDialog.isVisible()) {
                mLoadingDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMessageDialog(String title, String message) {
        try {
            if (mMessageDialog != null && mMessageDialog.isVisible()) mMessageDialog.dismiss();
            mMessageDialog.setTitle(title);
            mMessageDialog.setMessage(message);
            mMessageDialog.show(getSupportFragmentManager(), mMessageDialog.getClass().getSimpleName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showConfirmDialog(String title, String message, String positive, String negative, OnOkClickListener clickListener) {
        try {
            if (mConfirmDialog != null && mConfirmDialog.isVisible()) mConfirmDialog.dismiss();
            mConfirmDialog.setTitle(title);
            mConfirmDialog.setMessage(message);
            mConfirmDialog.setPositive(positive);
            mConfirmDialog.setNegative(negative);
            mConfirmDialog.setOnOkClickListener(clickListener);
            mConfirmDialog.show(getSupportFragmentManager(), mConfirmDialog.getClass().getSimpleName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
