package com.nhapt.base.mvvm;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nhapt.base.R;
import com.nhapt.base.BR;
import com.nhapt.base.listener.OnOkClickListener;
import com.nhapt.base.util.CommonUtil;

public abstract class MVVMBottomFragment<V extends BaseViewModel, D extends ViewDataBinding> extends BottomSheetDialogFragment implements Navigator {

    protected V mViewModel;

    protected D mDataBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false);
        mDataBinding.setLifecycleOwner(this);
        CommonUtil.setupUIAutoHideKeyboard(getActivity(), mDataBinding.getRoot());
        mViewModel = (V) ViewModelProviders.of(this).get(modelClass());
        mViewModel.setNavigator(this);
        mViewModel.onCreate();
        mDataBinding.setVariable(BR.viewModel, mViewModel);
        return mDataBinding.getRoot();
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
            MVVMFragment parent = (MVVMFragment) getParentFragment();
            parent.navigateTo(actionId);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void nestedNavigateTo(int actionId, Bundle bundle) {
        try {
            MVVMFragment parent = (MVVMFragment) getParentFragment();
            parent.navigateTo(actionId);
        } catch (Exception e){
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
