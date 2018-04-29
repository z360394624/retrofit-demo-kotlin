package com.tech.integer.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tech.integer.demo.entity.MyEntity
import com.tech.integer.demo.response.BaseResponse
import com.tech.integer.demo.service.MyService
import com.tech.integer.network.Network
import com.tech.integer.demo.R
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    companion object {
        val TAG = MainActivity::class.java.name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var retrofit = Network.retrofit("http://112.74.88.197:65432/")
        var myService : MyService = retrofit.create(MyService::class.java)
        var call  = myService.getData(MyEntity("balabalabala"))
        request_data.setOnClickListener {
            call.enqueue(object : Callback<BaseResponse> {

                override fun onFailure(call: Call<BaseResponse>?, t: Throwable?) {
                    Log.e(TAG, "Error: ${t?.cause}")
                }

                override fun onResponse(call: Call<BaseResponse>?, response: Response<BaseResponse>?) {
                    var baseResponse : BaseResponse ?= response?.body()
                    Log.i(TAG, "result = ${baseResponse?.flag}")
                }
            })
        }
    }
}
