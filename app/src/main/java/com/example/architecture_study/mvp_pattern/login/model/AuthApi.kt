package com.example.architecture_study.mvp_pattern.login.model

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {
    @FormUrlEncoded
    @POST("login/oauth/access_token")
    @Headers("Accept: application/json")
    fun getAccessToken( //아래 필드의 데이터를 인자로 받아 POST방식으로 정보를 전송하고
//GithubAccessToken에 정의된 데이터 형식으로 응답받음.
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String): Observable<GithubAccessToken>
}