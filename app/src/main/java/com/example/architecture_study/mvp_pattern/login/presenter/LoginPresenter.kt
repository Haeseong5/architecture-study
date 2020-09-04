package com.haeseong5.android.android_architecture_study.mvp_pattern.login.presenter

import android.content.Context
import android.net.Uri
import android.util.Log.*
import androidx.browser.customtabs.CustomTabsIntent
import com.example.architecture_study.BuildConfig
import com.example.architecture_study.R
import com.haeseong5.android.android_architecture_study.mvp_pattern.login.contract.LoginContract
import com.kakao.sdk.auth.AuthApi
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

/**
 * Presenter 는 뷰가 통지하는 이벤트를 받기 위해 Contract 의 Presenter 인터페이스를 구현한다.
 * 프레젠터가 뷰에 접근할 때는 액티비티 자체가 아니라 Contract에 선언된 인터페이스를 통해 조작한다.
 *
 * 프레젠터는 뷰의 구현에 대한 자세한 내용을 알 필요 없이 자신의 역할인 사용자의 액션을 처리하고
 * 모델에 접근하는 데만 전념할 수 있다.
 * 뷰도 자신의 역할인 표시에만 전념할 수 있다.
 *
 * 단점: 한줄로 구현된 메소드가 많아 복잡해지고,
 * 뷰와 프레젠터가 1:1관계를 이뤄 패키지와 클래스가 많아진다.
 * View 를 프로퍼티로 선언하였는데, 이 프로퍼티의 실체 클래스는 LoginActivity 이다.
 */
class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {
    private val TAG = this.javaClass.name
    internal val disposables = CompositeDisposable()

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

    override fun tryGithubLogin(context: Context) {
        /**
         *사용자 인증을 처리하는 URL 구성.
         * 형식: HTTPS:/ github.com/login/oauth/authorize?clint_id={client_id}
         */
        val authUri =
            Uri.Builder().scheme("https").authority("github.com")
                .appendPath("login")
                .appendPath("oauth")
                .appendPath("authorize")
                .appendQueryParameter(
                    "client_id",
                    context.getString(R.string.GITHUB_CLIENT_ID)
                )
                .build()
        //크롬 커스텀 탭으로 웹페이지 표시
        val intent = CustomTabsIntent.Builder().build()
        intent.launchUrl(context, authUri)    }

}