package com.nearsoft.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class MainService

fun main(args: Array<String>) {
    runApplication<MainService>(*args)
}
