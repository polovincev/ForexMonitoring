package ru.skynet.advisor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SkyNetAdvisorApplication

fun main(args: Array<String>) {
    runApplication<SkyNetAdvisorApplication>(*args)
}
