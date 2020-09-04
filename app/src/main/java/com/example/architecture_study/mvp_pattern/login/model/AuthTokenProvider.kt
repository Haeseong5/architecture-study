package com.example.architecture_study.mvp_pattern.login.model

import android.content.Context
import android.preference.PreferenceManager

/**
 * 액세스 토큰을 처리/관리하는 부분
 */
class AuthTokenProvider(private val context: Context) {
    //SharedPreference에 액세스 토큰을 저장
    fun updateToken(token: String) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
            .putString(KEY_AUTH_TOKEN, token)
            .apply()
    }

    //SharedPreference에 저장되어 있는 액세스 토큰 반환
//저장되어 있는 액세스 토큰이 없는 경우 널값 반환
    val token: String?
        get() = PreferenceManager.getDefaultSharedPreferences(context)
            .getString(KEY_AUTH_TOKEN, null)

    companion object {
        private val KEY_AUTH_TOKEN = "auth_token"
    }

}