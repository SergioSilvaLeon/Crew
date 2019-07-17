package com.nearsoft

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class CrewTaskApplication

fun main(args: Array<String>) {
    runApplication<CrewTaskApplication>(*args)
}

