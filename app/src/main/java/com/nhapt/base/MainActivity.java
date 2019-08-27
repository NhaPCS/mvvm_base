package com.nhapt.base;

import android.os.Bundle;

import androidx.navigation.Navigation;

import com.nhapt.base.mvvm.MVVMActivity;

public class MainActivity extends MVVMActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp();
    }
}
