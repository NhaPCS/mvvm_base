package com.nhapt.base.view_model;


import com.nhapt.base.mvvm.BaseViewModel;
import com.nhapt.base.navigator.ConfirmDialogNavigator;

public class ConfirmDialogViewModel extends BaseViewModel<ConfirmDialogNavigator> {

    public void onCancelClick() {
        getNavigator().onCancelClick();
    }

    public void onYesClick() {
        getNavigator().onOkClick();
    }
}
