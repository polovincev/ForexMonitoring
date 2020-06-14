package ru.skynet.advisor.security.jwt

import io.jsonwebtoken.*
import io.jsonwebtoken.io.Encoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import ru.skynet.advisor.security.service.UserDetailsImpl
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtUtils {

    @Value("\${bezkoder.app.jwtSecret}")
    private val jwtSecret: String = ""

    @Value("\${bezkoder.app.jwtExpirationMs}")
    private val jwtExpirationMs = 0

    fun generateJwtToken(authentication: Authentication): String {
        val userPrincipal: UserDetailsImpl = authentication.principal as UserDetailsImpl
        val now = Date()
        return Jwts.builder()
                .setSubject(userPrincipal.username)
                .setHeaderParam("typ", "JWT")
                .claim("data", "flow")
                .setIssuedAt(now)
                .setExpiration(Date(now.time + jwtExpirationMs))
                .signWith(getKey())
                .compact()

    }

    fun getUserNameFromJwtToken(token: String): String {
        return decodeToken(token).body.subject
    }

    fun validateJwtToken(token: String): Boolean {
        try {
            decodeToken(token)
            return true
        } catch (e: SignatureException) {
//            JwtUtils.logger.error("Invalid JWT signature: {}", e.message)
        } catch (e: MalformedJwtException) {
//            JwtUtils.logger.error("Invalid JWT token: {}", e.message)
        } catch (e: ExpiredJwtException) {
//            JwtUtils.logger.error("JWT token is expired: {}", e.message)
        } catch (e: UnsupportedJwtException) {
//            JwtUtils.logger.error("JWT token is unsupported: {}", e.message)
        } catch (e: IllegalArgumentException) {
//            JwtUtils.logger.error("JWT claims string is empty: {}", e.message)
        }
        return false
    }

    fun getKey(): SecretKey = Keys
            .hmacShaKeyFor(Encoders.BASE64.encode(jwtSecret.toByteArray()).toByteArray())

    fun decodeToken(token: String): Jws<Claims> =
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token)

}