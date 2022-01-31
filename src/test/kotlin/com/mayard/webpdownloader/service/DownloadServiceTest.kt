package com.mayard.webpdownloader.service

import com.mayard.webpdownloader.WebpDownloaderApplication
import com.mayard.webpdownloader.handler.MainHandler
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.StringSpec
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [WebpDownloaderApplication::class])
class DownloadServiceTest(
    var downloadService: DownloadService,
) : StringSpec() {

    init {
        "download service test" {
            shouldNotThrow<Exception> {
                downloadService.downloadFile("https://www.google.com/images/branding/googlelogo/2x/googlelogo_light_color_272x92dp.png")
            }
        }
    }
}