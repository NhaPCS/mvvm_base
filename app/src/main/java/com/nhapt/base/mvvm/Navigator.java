package com.nhapt.base.mvvm;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentManager;

import com.nhapt.base.listener.OnOkClickListener;


public interface Navigator {

    Context getBaseContext();

    MVVMActivity getBaseActivity();

    Application getApplication();

    View getBaseView();

    void runOnUIThread(Runnable runnable);

    FragmentManager getBaseFragmentManager();

    void showLoading();

    void hideLoading();

    void navigateTo(int actionId);

    void navigateTo(int actionId, Bundle bundle);

    void nestedNavigateTo(int actionId);

    void nestedNavigateTo(int actionId, Bundle bundle);

    void backToPrevious();

    void showErrorMessage(String message);

    void showMessage(String message);

    void showMessageDialog(String title, String message);

    <D extends ViewDataBinding> D getmDataBinding();

    void showConfirmDialog(String title, String message, OnOkClickListener okClickListener);

    void showConfirmDialog(String title, String message, String positive, String negative, OnOkClickListener okClickListener);

    void showConfirmDialog(String title, String message, String positive, OnOkClickListener okClickListener);
}
