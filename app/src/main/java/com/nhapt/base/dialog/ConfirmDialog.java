package com.nhapt.base.dialog;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nhapt.base.R;
import com.nhapt.base.BR;
import com.nhapt.base.databinding.DialogConfirmBinding;
import com.nhapt.base.listener.OnOkClickListener;
import com.nhapt.base.mvvm.MVVMDialog;
import com.nhapt.base.navigator.ConfirmDialogNavigator;
import com.nhapt.base.view_model.ConfirmDialogViewModel;

public class ConfirmDialog extends MVVMDialog<ConfirmDialogViewModel, DialogConfirmBinding> implements ConfirmDialogNavigator {

    private String message;
    private String title;
    private String positive;
    private String negative;
    private OnOkClickListener onOkClickListener;

    public void setOnOkClickListener(OnOkClickListener onOkClickListener) {
        this.onOkClickListener = onOkClickListener;
    }

    public void setPositive(String positive) {
        this.positive = positive;
    }

    public void setNegative(String negative) {
        this.negative = negative;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    protected int layoutId() {
        return R.layout.dialog_confirm;
    }

    @Override
    protected Class modelClass() {
        return ConfirmDialogViewModel.class;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getmDataBinding().lbTitle.setText(title);
        getmDataBinding().lbDesc.setText(message);
        if (positive != null)
            getmDataBinding().btnYes.setText(positive);
        if (negative != null)
            getmDataBinding().btnCancel.setText(negative);
    }

    @Override
    public void onOkClick() {
        if (onOkClickListener != null) onOkClickListener.onOkClick();
        dismissAllowingStateLoss();
    }

    @Override
    public void onCancelClick() {
        dismissAllowingStateLoss();
    }
}
