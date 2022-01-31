package com.mayard.webpdownloader

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@ComponentScan(basePackages = ["com.mayard.webpdownloader.*"])
@SpringBootApplication
class WebpDownloaderApplication

fun main(args: Array<String>) {
    runApplication<WebpDownloaderApplication>(*args)
}
