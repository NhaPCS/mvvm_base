package com.nhapt.base.data.response;

public class BaseResponse<D extends Object> {
    private int code;
    private long currentTime;
    private String message;
    private D response;
    private D respone;
    private D data;

    public D getResponse() {
        if(response!=null) return response;
        if(respone!=null) return respone;
        return data;
    }

    public boolean isSuccess() {
        return code == 200;
    }

    public int getCode() {
        return code;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public String getMessage() {
        return message;
    }
}
