package ru.skynet.advisor.dao.impl

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import ru.skynet.advisor.dao.UserDao
import ru.skynet.advisor.models.User
import java.sql.ResultSet

@Component
class UserDaoImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : UserDao {

    override fun findByUsername(username: String): User? {
        val query = """SELECT ID, USERNAME, EMAIL, PASSWORD 
            |FROM USERS WHERE USERNAME = :username""".trimMargin()

        val params = mapOf(
                "username" to username
        )

        return try {
            jdbcTemplate.queryForObject(query, params) { rs: ResultSet, _: Int ->
                User(
                    id = rs.getLong("ID"),
                    username = rs.getString("USERNAME"),
                    password = rs.getString("PASSWORD"),
                    email = rs.getString("EMAIL")
                )
            }
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }
}