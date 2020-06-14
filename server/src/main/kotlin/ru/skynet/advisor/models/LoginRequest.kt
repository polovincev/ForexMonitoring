package ru.skynet.advisor.models

data class LoginRequest(
    var username: String,
    val password: String
)