package com.haeseong5.android.android_architecture_study.mvp_pattern.main

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import com.bumptech.glide.Glide
import com.example.architecture_study.R
import com.example.architecture_study.mvp_pattern.main.contract.MainContract
import com.example.architecture_study.mvp_pattern.main.model.Profile
import com.example.architecture_study.mvp_pattern.main.presenter.MainPresenter
import com.haeseong5.android.android_architecture_study.mvp_pattern.login.presenter.LoginPresenter
import com.haeseong5.android.android_architecture_study.mvp_pattern.login.view.LoginActivity
import com.kakao.sdk.common.util.Utility
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.progressDialog
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var mProgressDialog: ProgressDialog
    private val presenter by lazy {
        MainPresenter(
            this@MainActivity
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.setProfileInfo()
        main_bt_logout.setOnClickListener {
            presenter.tryLogout()
        }
    }

    override fun launchLoginActivity() {
        finish()
        startActivity<LoginActivity>()
    }

    override fun showProgress() {
        mProgressDialog = indeterminateProgressDialog(message = "Please Wait..."){
            show()
        }
    }

    override fun hideProgress() {
        mProgressDialog.dismiss()
    }

    override fun setProfileInfo(profile: Profile) {
        if (profile.profileImageUrl != null) {
            Glide.with(this@MainActivity)
                .load(profile.profileImageUrl)
                .into(main_iv_profile_image)
        }
        main_tv_profile_info.text = (
                    "아이디: ${profile.id.toString()} \n" +
                    "이메일: ${profile.email.toString()} \n" +
                    "닉네임: ${profile.nickname}  \n"
                )
    }

}
