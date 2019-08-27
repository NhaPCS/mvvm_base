package com.nhapt.base.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.bumptech.glide.load.resource.bitmap.TransformationUtils.rotateImage;

public class FileUtil {

    public static String resizeImageFile(String filePath, boolean scaleable) {
        try {
            Bitmap b = BitmapFactory.decodeFile(filePath);
            if (b == null) return filePath;
            Bitmap out = b;
            if (scaleable)
                out = Bitmap.createScaledBitmap(b, b.getWidth() / 4, b.getHeight() / 4, false);

            ExifInterface ei = null;
            try {
                Bitmap main;
                ei = new ExifInterface(filePath);
                int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);

                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        main = rotateImage(out, 90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        main = rotateImage(out, 180);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        main = rotateImage(out, 270);
                        break;
                    default:
                        main = out;
                        break;
                }

                FileOutputStream fOut;
                try {
                    fOut = new FileOutputStream(new File(filePath));
                    main.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                    fOut.flush();
                    fOut.close();
                    b.recycle();
                    main.recycle();
                } catch (Exception e) {
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return filePath;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
