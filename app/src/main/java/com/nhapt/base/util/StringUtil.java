package com.nhapt.base.util;

import android.content.Context;
import android.content.pm.PackageInfo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;

public class StringUtil {

    public static String md5(String text) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance("MD5");
            digest.update(text.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getVersionName(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static long getNumberLong(String text) {
        try {
            return Long.parseLong(text.replaceAll("[^\\d]", "").trim());
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public static String priceText(String text) {
        DecimalFormat df = new DecimalFormat("#,###.##");
        String v = text.replace(String.valueOf(df.getDecimalFormatSymbols().getGroupingSeparator()), "");
        try {
            Number n = df.parse(v);
            return df.format(n);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return text;
    }

}
