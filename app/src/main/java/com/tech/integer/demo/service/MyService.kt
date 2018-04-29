package com.tech.integer.demo.service

import com.tech.integer.demo.entity.MyEntity
import com.tech.integer.demo.response.BaseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface MyService {


    @POST("blog/article/articleList")
    fun getData(@Body data : MyEntity) : Call<BaseResponse>


}