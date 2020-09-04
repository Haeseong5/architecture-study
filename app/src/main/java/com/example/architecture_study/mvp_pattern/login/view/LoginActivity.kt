package com.haeseong5.android.android_architecture_study.mvp_pattern.login.view

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.architecture_study.ApplicationClass
import com.example.architecture_study.R
import com.haeseong5.android.android_architecture_study.mvp_pattern.login.contract.LoginContract
import com.haeseong5.android.android_architecture_study.mvp_pattern.login.presenter.LoginPresenter
import com.haeseong5.android.android_architecture_study.mvp_pattern.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity(), LoginContract.View {
    private val TAG: String = this.javaClass.name
    private lateinit var mProgressDialog: ProgressDialog
    //1. Presenter 인스턴스 생성
    //lazy :
    private val presenter by lazy {
        LoginPresenter(
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ApplicationClass.getHashKey(this@LoginActivity)

        login_kakao_button.setOnClickListener {
            //presenter 에 카카오 로그인을 시도한다는 것을 알린다.
            presenter.tryKakaoLogin(this@LoginActivity)
        }

        login_github_button.setOnClickListener {
            presenter.tryGithubLogin(this@LoginActivity)
        }
    }

    override fun launchMainActivity() {
        finish()
        startActivity<MainActivity>() //using Anko Library
    }

    override fun showProgress() {
        mProgressDialog = indeterminateProgressDialog(message = "Please Wait..."){
            show()
        }
    }

    override fun hideProgress() {
        mProgressDialog.dismiss()

    }


    //SignInActivity가 화면에 표시되고 있으면 onCreate() 대신 onNewIntent()호출
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        showProgress()
        //엘비스 연산자를 사용하여 null check
////        사용자 인증 완료 후 리디렉션 주소를 가져온다.
//        val uri = intent?.data ?: throw IllegalArgumentException("No data exists")
//        //주소에서 액세스 토큰 교환에 필요한 코드를 추출
//        val code = uri.getQueryParameter("code") ?: throw IllegalStateException("No code exists")
//        getAccessToken(code)
    }

}
