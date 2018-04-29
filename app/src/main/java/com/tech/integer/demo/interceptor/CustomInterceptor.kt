package com.tech.integer.demo.interceptor

import com.google.gson.Gson
import com.tech.integer.network.entity.BaseEntity
import com.tech.integer.network.entity.CustomEntity
import okhttp3.*
import okio.Okio
import java.io.ByteArrayOutputStream
import java.nio.charset.Charset

class CustomInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {

        // 需要包装的Request
        val oldRequest = chain?.request()
        // 取报文体
        var body = oldRequest?.body()

        val baos = ByteArrayOutputStream()

        val sink = Okio.sink(baos)

        val bufferedSink = Okio.buffer(sink)

        body?.writeTo(bufferedSink)

        // 请求题转字符串
        var requestString : String =  bufferedSink.buffer().readUtf8()

        /**
         *
         * 对字符串requestString加密 balabala
         *
         */

        // 转对象
        var baseEntity : BaseEntity = Gson().fromJson(requestString, BaseEntity::class.java)

        // 公参
        var commonParams = HashMap<String, String>()
        commonParams.put("key1","param1")
        commonParams.put("key2","param2")

        // 公参和实参装一起
        var customEntity : CustomEntity = CustomEntity(commonParams, baseEntity)

        // 用customEntity创建新的RequestBody
        var requestBody = RequestBody.create(MediaType.parse("application/json"), Gson().toJson(customEntity))

        // 用requestBody创建新的Request
        var request : Request = Request.Builder()
                .method(oldRequest?.method(), requestBody)
                .url(oldRequest?.url())
                .headers(oldRequest?.headers())
                .build()

        return chain?.proceed(request)!!
    }

}