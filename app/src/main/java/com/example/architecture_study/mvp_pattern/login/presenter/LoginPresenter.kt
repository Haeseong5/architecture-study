package com.haeseong5.android.android_architecture_study.mvp_pattern.login.presenter

import android.content.Context
import android.util.Log.*
import com.haeseong5.android.android_architecture_study.mvp_pattern.login.contract.LoginContract
import com.kakao.sdk.auth.LoginClient

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {
    private val TAG = this.javaClass.name

    override fun tryKakaoLogin(context: Context) {
        // 카카오계정으로 로그인
//        view.launchMainActivity()
        LoginClient.instance.run {
            if(isKakaoTalkLoginAvailable(context)){
                loginWithKakaoAccount(context) { token, error ->
                    if (error != null) {
                        e(TAG, "로그인 실패", error)
                    }
                    else if (token != null) {
                        i(TAG, "로그인 성공 ${token.accessToken}")
                        view.launchMainActivity()
                    }
                }
            }else{
                d(TAG, "카카오톡 미설치")
            }
        }
    }
}