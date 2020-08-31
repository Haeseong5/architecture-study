package com.example.architecture_study.mvp_pattern.main.presenter

import android.util.Log.e
import android.util.Log.i
import com.example.architecture_study.mvp_pattern.main.contract.MainContract
import com.example.architecture_study.mvp_pattern.main.model.Profile
import com.kakao.sdk.user.UserApiClient

/**
 * Contract 의 View 를 프로퍼티로 선언하고, Contract의 Presenter Interface를 구현한다.
 */
class MainPresenter(private val view: MainContract.View): MainContract.Presenter {

    private val TAG = this.javaClass.name

    override fun setProfileInfo() {
        // 사용자 정보 요청 (기본)
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                e(TAG, "사용자 정보 요청 실패", error)
            }
            else if (user != null) {
                i(TAG, "사용자 정보 요청 성공" +
                        "\n회원번호: ${user.id}" +
                        "\n이메일: ${user.kakaoAccount?.email}" +
                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                        "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")
                val profile = Profile(
                    user.id,
                    user.kakaoAccount?.email,
                    user.kakaoAccount?.profile?.nickname,
                    user.kakaoAccount?.profile?.thumbnailImageUrl
                )
                view.setProfileInfo(profile)
            }
        }
    }

    override fun tryLogout() {
        view.showProgress()
        UserApiClient.instance.logout { error ->
            if (error != null) {
                e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
            }
            else {
                i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
                view.launchLoginActivity()
            }
            view.hideProgress()
        }
    }

    override fun tryUnLink() {
// 연결 끊기
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                e(TAG, "연결 끊기 실패", error)
            }
            else {
                i(TAG, "연결 끊기 성공. SDK에서 토큰 삭제 됨")
            }
        }
    }

}