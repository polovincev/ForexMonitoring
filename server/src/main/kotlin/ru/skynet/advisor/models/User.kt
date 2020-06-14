package ru.skynet.advisor.models

data class User(
    val id: Long,
    val username: String,
    val email: String,
    val password: String,
    val roles: List<Role>? = null
)