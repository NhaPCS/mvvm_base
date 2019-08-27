package com.nhapt.base.data;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.nhapt.base.data.response.BaseResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static APIClient ourInstance;
    private APIService apiService;
    private Context context;

    public static APIClient getInstance() {
        return ourInstance;
    }

    public static void newInstance(Context context) {
        if (ourInstance == null || ourInstance.apiService == null) {
            ourInstance = new APIClient(context);
            ourInstance.apiService = ourInstance.generateAPIService();
        }
    }


    public APIClient(Context context) {
        this.context = context;
    }

    private APIService generateAPIService() {
        if (apiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(getBaseUrl())
                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(createOkHttpClient())
                    .build();
            apiService = retrofit.create(APIService.class);
        }
        return apiService;
    }

    public APIService getApiService() {
        return apiService;
    }

    /**
     *
     */
    private OkHttpClient createOkHttpClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(s -> Log.e("RESPONSE", s));
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder();
        httpClient.addInterceptor(interceptor);
        httpClient.connectTimeout(60, TimeUnit.SECONDS);
        httpClient.readTimeout(60, TimeUnit.SECONDS);
        httpClient.writeTimeout(60, TimeUnit.SECONDS);
        httpClient.addInterceptor(chain -> {
            final Request original = chain.request().newBuilder()
                    .build();
            final Request.Builder requestBuilder = original.newBuilder();

            return chain.proceed(requestBuilder.build());
        });
        return httpClient.build();
    }

    private String getBaseUrl() {
        return "https://api.finbay.us/v1/";
    }

    public Map<String, Object> getDefaultParams() {
        long currentTime = new Date().getTime();
        Map<String, Object> params = new HashMap<>();
        return params;
    }

    public Map<String, RequestBody> getDefaultParts() {
        long currentTime = new Date().getTime();
        Map<String, RequestBody> params = new HashMap<>();
        return params;
    }

    public static RequestBody getRequestBody(String object) {
        return RequestBody.create(object, MediaType.parse("text/plain"));
    }

    public static <D extends Object> void getResponse(Call<BaseResponse<D>> call, ICallback<D> callback) {
        call.enqueue(new Callback<BaseResponse<D>>() {
            @Override
            public void onResponse(Call<BaseResponse<D>> call, retrofit2.Response<BaseResponse<D>> response) {
                Log.e("ERR", new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (response.body().isSuccess()) {
                        callback.onSuccess(response.body().getResponse());
                    } else callback.onFailed(response.body().getMessage());
                } else {
                    callback.onFailed(response.message());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<D>> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });

    }
}
