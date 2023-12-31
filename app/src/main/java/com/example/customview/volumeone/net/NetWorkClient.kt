package com.example.customview.volumeone.net

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetWorkClient {

    private fun getClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .build()
    }

     fun getService() =
        Retrofit.Builder()
            .client(getClient())
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl("https://pbs.twimg.com/")
            .build().create(
                Service::class.java
            )

}