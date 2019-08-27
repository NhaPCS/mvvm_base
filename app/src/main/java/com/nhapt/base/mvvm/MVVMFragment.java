package com.nhapt.base.mvvm;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.nhapt.base.R;
import com.nhapt.base.listener.OnOkClickListener;
import com.nhapt.base.util.CommonUtil;


public abstract class MVVMFragment<V extends BaseViewModel, D extends ViewDataBinding> extends Fragment implements Navigator {

    protected V mViewModel;

    protected D mDataBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("onCreate", getClass().getSimpleName());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mDataBinding == null) {
            mDataBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false);
            mDataBinding.setLifecycleOwner(this);
            mViewModel = (V) ViewModelProviders.of(this).get(modelClass());
            mViewModel.setNavigator(this);
            mViewModel.onCreate();
            mDataBinding.setVariable(com.nhapt.base.BR.viewModel, mViewModel);
            CommonUtil.setupUIAutoHideKeyboard(getActivity(), mDataBinding.getRoot());
        }
        return mDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract int layoutId();

    protected abstract Class modelClass();

    @Nullable
    @Override
    public Context getBaseContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    public Application getApplication() {
        return getActivity().getApplication();
    }

    @Override
    public MVVMActivity getBaseActivity() {
        return (MVVMActivity) getActivity();
    }

    @Override
    public View getBaseView() {
        return getView();
    }

    @Override
    public void runOnUIThread(Runnable runnable) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(runnable);
        }
    }

    @Override
    public FragmentManager getBaseFragmentManager() {
        return getChildFragmentManager();
    }

    @Override
    public void showLoading() {
        if (getBaseActivity() != null) getBaseActivity().showLoading();
    }

    @Override
    public void hideLoading() {
        if (getBaseActivity() != null) getBaseActivity().hideLoading();
    }

    @Override
    public void navigateTo(int actionId) {
        if (getBaseView() != null)
            Navigation.findNavController(getBaseView()).navigate(actionId);
    }

    @Override
    public void navigateTo(int actionId, Bundle bundle) {
        if (getBaseView() != null)
            Navigation.findNavController(getBaseView()).navigate(actionId, bundle);
    }

    @Override
    public void nestedNavigateTo(int actionId) {
        try {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(actionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void nestedNavigateTo(int actionId, Bundle bundle) {
        try {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(actionId, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void backToPrevious() {
        if (getBaseView() != null)
            Navigation.findNavController(getBaseView()).popBackStack();
    }

    @Override
    public void onDestroy() {
        if (mViewModel != null) mViewModel.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showMessage(String message) {
        if (getBaseActivity() != null) getBaseActivity().showMessageDialog("", message);
    }

    @Override
    public void showMessageDialog(String title, String message) {
        if (getBaseActivity() != null) getBaseActivity().showMessageDialog(title, message);
    }

    @Override
    public void showErrorMessage(String message) {
        if (getBaseActivity() != null)
            getBaseActivity().showMessageDialog(getString(R.string.failed), message);
    }

    @Override
    public void showConfirmDialog(String title, String message, OnOkClickListener okClickListener) {
        if (getBaseActivity() != null)
            getBaseActivity().showConfirmDialog(title, message, null, null, okClickListener);
    }

    @Override
    public void showConfirmDialog(String title, String message, String positive, OnOkClickListener okClickListener) {
        if (getBaseActivity() != null)
            getBaseActivity().showConfirmDialog(title, message, positive, null, okClickListener);
    }

    @Override
    public void showConfirmDialog(String title, String message, String positive, String negative, OnOkClickListener okClickListener) {
        if (getBaseActivity() != null)
            getBaseActivity().showConfirmDialog(title, message, positive, negative, okClickListener);
    }

    @Override
    public D getmDataBinding() {
        return mDataBinding;
    }
}
