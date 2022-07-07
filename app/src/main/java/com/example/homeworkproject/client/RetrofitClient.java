package com.example.homeworkproject.client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

    private static final Retrofit retrofit = retrofitBuilder.build();

    private static final Api myApi = retrofit.create(Api.class);

    public static Api getMyApi(){
        return myApi;
    }
}
