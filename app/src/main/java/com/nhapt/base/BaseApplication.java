package com.nhapt.base;

import android.app.Application;
import android.content.Context;

import com.nhapt.base.data.APIClient;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        APIClient.newInstance(this);
//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                .setDefaultFontPath("san.ttf")
//                .setFontAttrId(R.attr.fontPath)
//                .build()
//        );
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));

    }
}
