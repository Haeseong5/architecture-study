package com.example.architecture_study.mvp_pattern.main.model

data class Profile (
    val id: Long,
    val email: String?,
    val nickname: String?,
    val profileImageUrl: String?
)