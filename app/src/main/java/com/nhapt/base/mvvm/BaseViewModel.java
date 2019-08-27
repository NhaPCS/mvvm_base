package com.nhapt.base.mvvm;

import androidx.lifecycle.ViewModel;

import java.lang.ref.WeakReference;

public class BaseViewModel<N extends Navigator> extends ViewModel {

    private WeakReference<N> mNavigator;

    public N getNavigator() {
        return mNavigator.get();
    }

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

    public void onBackClick() {
        getNavigator().backToPrevious();
    }

    public void onCreate(){

    }

    public void onDestroy(){

    }
}
