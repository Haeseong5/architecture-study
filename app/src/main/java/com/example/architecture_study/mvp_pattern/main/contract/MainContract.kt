package com.example.architecture_study.mvp_pattern.main.contract

import com.example.architecture_study.mvp_pattern.main.model.Profile

class MainContract {
    interface View {
        fun launchLoginActivity()
        fun showProgress()
        fun hideProgress()
        fun setProfileInfo(profile: Profile)
    }

    interface Presenter {
        fun setProfileInfo()
        fun tryLogout()
        fun tryUnLink()
    }
}