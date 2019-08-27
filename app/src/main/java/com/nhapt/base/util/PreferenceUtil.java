package com.nhapt.base.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by USER on 16/9/2016.
 */
public class PreferenceUtil {
    private static final String PRE_OBJ_USER_ID = "PRE_OBJ_USER_ID";
    private static final String PRE_ACCESS_TOKEN = "PRE_ACCESS_TOKEN";
    private static final String PRE_DATA_STATIC = "PRE_DATA_STATIC";
    private static final String PRE_USER_INFO = "PRE_USER_INFO";
    private static final String PRE_IS_INVESTOR = "PRE_IS_INVESTOR";

    /*========================== GET ===============================================*/

    public static int getInt(Context context, String key) {
        if (context == null) return 0;
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(key, 0);
    }

    public static String getString(Context context, String key) {
        if (context == null) return "";
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, "");
    }

    public static boolean getBoolean(Context context, String key) {
        if (context == null) return false;
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, false);
    }


    /*======================================= SET =====================================*/

    public static void setInt(Context context, String key, int value) {
        if (context == null) return;
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor ed = sh.edit();
        ed.putInt(key, value);
        ed.commit();
    }

    public static void setString(Context context, String key, String value) {
        if (context == null) return;
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor ed = sh.edit();
        ed.putString(key, value);
        ed.commit();
    }

    public static void setBoolean(Context context, String key, boolean value) {
        if (context == null) return;
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor ed = sh.edit();
        ed.putBoolean(key, value);
        ed.commit();
    }

    /*======================================= FUNCTIONS =====================================*/

    public static void setAccessToken(Context context, String token) {
        setString(context, PRE_ACCESS_TOKEN, token);
    }

    public static String getAccessToken(Context context) {
        return getString(context, PRE_ACCESS_TOKEN);
    }

    public static void setObjUserId(Context context, String userId) {
        setString(context, PRE_OBJ_USER_ID, userId);
    }

    public static String getObjUserId(Context context) {
        return getString(context, PRE_OBJ_USER_ID);
    }
}
