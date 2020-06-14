package ru.skynet.advisor.security.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.skynet.advisor.dao.UserDao

@Service
class UserDetailsServiceImpl(
        private val userDao: UserDao
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails? {
        val user = userDao.findByUsername(username)
                ?: throw UsernameNotFoundException("User Not Found with username: $username")

        return UserDetailsImpl(user)
    }
}