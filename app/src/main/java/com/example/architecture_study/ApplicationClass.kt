package com.example.architecture_study

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.architecture_study.R
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility


class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()
        // 다른 초기화 코드들

        // Kakao SDK 초기화
        KakaoSdk.init(this, getString(R.string.kakao_native_app_key))
    }
    companion object{
        fun getHashKey(context: Context){
            val keyHash = Utility.getKeyHash(context)
            Log.d("HASH KEY", keyHash)
        }
    }

}