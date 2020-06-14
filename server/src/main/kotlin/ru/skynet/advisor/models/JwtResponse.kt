package ru.skynet.advisor.models

data class JwtResponse(
    var token: String,
    val type: String = "Bearer",
    val id: Long,
    val username: String,
    val email: String,
    val roles: List<String>
)