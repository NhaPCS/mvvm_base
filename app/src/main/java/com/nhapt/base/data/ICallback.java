package com.nhapt.base.data;

public interface ICallback<D extends Object> {

    void onSuccess(D response);

    void onFailed(String message);
}
