package com.tech.integer.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Network {


    fun retrofit(baseUrl: String) : Retrofit {
        var retrofit = Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .client(buildOkhttp())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        return retrofit
    }

    var interceptor : Interceptor? = null


    fun init(i : Interceptor) {
        this.interceptor = i
    }

    private fun buildOkhttp(): OkHttpClient {
        var okhttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
//                .readTimeout()
//                .writeTimeout()
//                .connectTimeout()
                .build()
        return okhttpClient
    }

}