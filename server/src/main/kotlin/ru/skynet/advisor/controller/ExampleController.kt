package ru.skynet.advisor.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.skynet.advisor.dao.UserDao
import ru.skynet.advisor.dto.HelloDto
import ru.skynet.advisor.models.User

@RestController
class ExampleController(
    private val userDao: UserDao
) {
    @GetMapping("/api/hello")
    fun hello() = HelloDto("Hello world!")

    @GetMapping("/api/user/{username}")
    fun getUser(@PathVariable username: String): User? {
        return userDao.findByUsername(username)
    }
}
