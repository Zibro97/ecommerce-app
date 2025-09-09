package com.zibro.ecommerce.di

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "594ffab7d9f77121a2b3046d3984381a")
    }
}