package com.mayard.webpdownloader.router

import com.mayard.webpdownloader.handler.MainHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.coRouter
import org.springframework.web.reactive.function.server.router

@Configuration
class MainRouter(
    private val mainHandler: MainHandler
){

    @Bean
    fun router() = coRouter {
        "/".nest {
            listOf(
                GET("", mainHandler::downloadImage)
            )
        }
    }
}