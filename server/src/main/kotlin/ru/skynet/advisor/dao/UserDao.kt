package ru.skynet.advisor.dao

import ru.skynet.advisor.models.User

interface UserDao {
    fun findByUsername(username: String): User?
}