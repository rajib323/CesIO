package com.google.cesio;

import com.google.cesio.Model.Results;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
    Call<Results> getResults(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );

    @GET("everything")
    Call<Results> getQuery(
            @Query("q") String query,
            @Query("apiKey") String apiKey
    );
}
