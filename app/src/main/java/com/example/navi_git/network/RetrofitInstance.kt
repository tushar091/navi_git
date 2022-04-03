package com.example.navi_git.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private const val BASE_URL = "https://api.github.com/repos/"
    private const val TIMEOUT_SECS = 30L

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(TimeoutInterceptor())
        .connectTimeout(TIMEOUT_SECS, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getRetrofit(): Retrofit {
        return retrofit
    }
}