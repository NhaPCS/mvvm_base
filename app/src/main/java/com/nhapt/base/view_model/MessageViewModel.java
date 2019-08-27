package com.nhapt.base.view_model;


import com.nhapt.base.mvvm.BaseViewModel;
import com.nhapt.base.navigator.MessageNavigator;

public class MessageViewModel extends BaseViewModel<MessageNavigator> {

    public void onOkClick() {
        getNavigator().onCloseDialog();
    }

}
