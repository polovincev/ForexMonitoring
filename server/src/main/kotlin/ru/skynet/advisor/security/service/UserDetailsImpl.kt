package ru.skynet.advisor.security.service

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.skynet.advisor.models.User

data class UserDetailsImpl(
        val id: Long,
        private val username: String,
        val email: String,
        private val password: String,
        private val authorities: Collection<GrantedAuthority>
): UserDetails {

    constructor(user: User): this(
        id = user.id,
        username = user.username,
        email = user.email,
        password = user.password,
        authorities = listOf(SimpleGrantedAuthority("ROLE_ADMIN"))
    )

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return username
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }


}