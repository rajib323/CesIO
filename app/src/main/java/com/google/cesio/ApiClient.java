package com.google.cesio;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String baseurl="https://newsapi.org/v2/";
    private static ApiClient apiClient;
    private static Retrofit retrofit;

    private ApiClient(){
        retrofit=new Retrofit.Builder().baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create()).build();

    }


    public static synchronized ApiClient getInstance(){
        if(apiClient==null)
            apiClient=new ApiClient();
        return apiClient;
    }

    public ApiInterface getApi(){
        return retrofit.create(ApiInterface.class);
    }

}
