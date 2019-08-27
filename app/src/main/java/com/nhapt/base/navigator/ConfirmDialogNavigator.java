package com.nhapt.base.navigator;

import com.nhapt.base.mvvm.Navigator;

public interface ConfirmDialogNavigator extends Navigator {
    void onOkClick();

    void onCancelClick();
}
