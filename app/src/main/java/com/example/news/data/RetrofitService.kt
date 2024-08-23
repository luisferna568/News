package com.example.news.data;

import com.example.news.models.RemoteResult

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface RetrofitService {

    @GET("v2/everything")
    suspend fun listNews(
            @Query("q") keyword: String,
            @Query("from") datePublished: String,
            @Query("sortBy") sortBy: String,
            @Query("apiKey") apiKey: String,
            @Query("language") language: String
    ): RemoteResult
}

object RetrofitServiceFactory {
    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitService::class.java)
    }
}