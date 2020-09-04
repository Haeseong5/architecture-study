package com.haeseong5.android.android_architecture_study.mvp_pattern.login.contract

import android.content.Context

/**
 * Contract 에는 View 와 Presenter 가 구현해야 할 인터페이스가 정의되어 있다.
 *
 */
class LoginContract {
    //View(액티비티)가 구현해야 할 인터페이스
    //Presenter가 View를 조작할 때 이용한다.
    interface View {
        fun launchMainActivity()
        fun showProgress()
        fun hideProgress()
    }
    //뷰에서 사용자 이벤트가 발생했을 때 등등 뷰가 프레젠터에 알릴 때 사용한다.
    //User Action 발생 시 수행할 작업 정의
    interface Presenter {
        fun tryKakaoLogin(context: Context)
        fun tryRxKakaoLogin(context: Context)
        fun tryGithubLogin(context: Context)

//        fun tryGitHubLogin(context: Context)
//        fun getAccessToken(code: String)
    }
}