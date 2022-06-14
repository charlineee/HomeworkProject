package com.example.homeworkproject.client;

import static okhttp3.internal.Internal.instance;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance = null;

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static Api myApi = retrofit.create(Api.class);

    public static Api getMyApi(){
        return myApi;
    }
}
