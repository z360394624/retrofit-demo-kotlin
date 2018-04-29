package com.tech.integer.demo

import android.app.Application
import com.tech.integer.demo.interceptor.CustomInterceptor
import com.tech.integer.network.Network

class DemoApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        Network.init(CustomInterceptor())

    }

}