package com.haeseong5.android.android_architecture_study.mvp_pattern.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.architecture_study.ApplicationClass
import com.example.architecture_study.R
import com.haeseong5.android.android_architecture_study.mvp_pattern.login.contract.LoginContract
import com.haeseong5.android.android_architecture_study.mvp_pattern.login.presenter.LoginPresenter
import com.haeseong5.android.android_architecture_study.mvp_pattern.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity(), LoginContract.View {
    private val TAG: String = this.javaClass.name

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
            presenter.tryKakaoLogin(this@LoginActivity)
        }
    }

    override fun launchMainActivity() {
        startActivity<MainActivity>() //using Anko Library
    }

}
