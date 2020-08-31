package com.haeseong5.android.android_architecture_study.mvp_pattern.login.contract

import android.content.Context

class LoginContract {
    interface View {
        fun launchMainActivity()
        fun showProgress()
        fun hideProgress()
    }
    interface Presenter {
        fun tryKakaoLogin(context: Context)
        fun tryRxKakaoLogin(context: Context)
    }
}