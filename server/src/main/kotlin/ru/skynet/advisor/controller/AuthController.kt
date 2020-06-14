package ru.skynet.advisor.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import ru.skynet.advisor.models.JwtResponse
import ru.skynet.advisor.models.LoginRequest
import ru.skynet.advisor.security.jwt.JwtUtils
import ru.skynet.advisor.security.service.UserDetailsImpl
import java.util.stream.Collectors

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
class AuthController(
        private val jwtUtils: JwtUtils,
        private val authenticationManager: AuthenticationManager
) {

    @PostMapping("/signin")
    fun authenticateUser(@RequestBody loginRequest: LoginRequest): ResponseEntity<*>? {
        val authentication: Authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password))
        SecurityContextHolder.getContext().authentication = authentication

        val jwt = jwtUtils.generateJwtToken(authentication)
        val userDetails: UserDetailsImpl = authentication.principal as UserDetailsImpl
        val roles: List<String> = userDetails.authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())

        return ResponseEntity.ok<Any>(JwtResponse(
                token = jwt,
                id = userDetails.id,
                username = userDetails.username,
                email = userDetails.email,
                roles = roles
        ))
    }
}