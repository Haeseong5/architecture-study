package com.haeseong5.android.android_architecture_study.mvp_pattern.login.presenter

import android.content.Context
import android.util.Log.*
import com.haeseong5.android.android_architecture_study.mvp_pattern.login.contract.LoginContract
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {
    private val TAG = this.javaClass.name

    override fun tryKakaoLogin(context: Context) {
        view.showProgress()
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                e(TAG, "로그인 실패", error)
            }
            else if (token != null) {
                i(TAG, "로그인 성공 ${token.accessToken}")
                view.launchMainActivity() //로그인에 성공하면 메인액티비티로 이동
//                finish()
            }
            view.hideProgress()
        }

// 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (LoginClient.instance.isKakaoTalkLoginAvailable(context)) {
            LoginClient.instance.loginWithKakaoTalk(context, callback = callback)
        } else {
            LoginClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }

    override fun tryRxKakaoLogin(context: Context) {

// 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
//        Single.just(LoginClient.instance.isKakaoTalkLoginAvailable(context))
//            .flatMap { available ->
//                if (available) LoginClient.rx.loginWithKakaoTalk(context)
//                else LoginClient.rx.loginWithKakaoAccount(context)
//            }
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ token ->
//                i(TAG, "로그인 성공 ${token.accessToken}")
//            }, { error ->
//                e(TAG, "로그인 실패", error)
//            })
//            .addTo(disposables)
    }

}