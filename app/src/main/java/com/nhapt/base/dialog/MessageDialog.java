package com.nhapt.base.dialog;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nhapt.base.R;
import com.nhapt.base.BR;
import com.nhapt.base.databinding.DialogMessageBinding;
import com.nhapt.base.mvvm.MVVMDialog;
import com.nhapt.base.navigator.MessageNavigator;
import com.nhapt.base.view_model.MessageViewModel;

public class MessageDialog extends MVVMDialog<MessageViewModel, DialogMessageBinding> implements MessageNavigator {

    private String message;
    private String title;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    protected int layoutId() {
        return R.layout.dialog_message;
    }

    @Override
    protected Class modelClass() {
        return MessageViewModel.class;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getmDataBinding().lbTitle.setText(title);
        getmDataBinding().lbDesc.setText(message);
    }

    @Override
    public void onCloseDialog() {
        dismissAllowingStateLoss();
    }
}
