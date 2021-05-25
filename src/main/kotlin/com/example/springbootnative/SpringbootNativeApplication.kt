package com.example.springbootnative

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@EnableConfigurationProperties(MyConfig::class, OtherConfig::class)
class SpringbootNativeApplication

fun main(args: Array<String>) {
    runApplication<SpringbootNativeApplication>(*args)
}

@ConfigurationProperties("myconfig")
class MyConfig(
    var greeting: String = "Hi...",
    var spaceshipdestination: String="Jupiter")

@ConfigurationProperties("otherconfig")
class OtherConfig(
    var fruit: String = "Pineapple"
    )

@RestController
@RequestMapping("/api")
class MyRestController(
    val config: MyConfig = MyConfig(),
    val otherConfig: OtherConfig = OtherConfig()
) {
    @GetMapping("/hello")
    fun hello(): String {
        return config.greeting;
    }

    @GetMapping("/fruit")
    fun gimmeFruit(): String {
        return otherConfig.fruit;
    }

}

