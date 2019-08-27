package com.nhapt.base.data;

import com.nhapt.base.data.response.BaseResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    int PAGE_SIZE = 30;
    int APP_LIVE = 0;

    @FormUrlEncoded
    @POST
    Call<BaseResponse<Object>> post(@FieldMap Map<String, Object> map);
}
