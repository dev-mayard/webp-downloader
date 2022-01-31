package com.mayard.webpdownloader.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.springframework.http.InvalidMediaTypeException
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

@Service
class DownloadService {

    companion object {
        val ImageTypes = listOf(
            MediaType.IMAGE_GIF,
            MediaType.IMAGE_JPEG,
            MediaType.IMAGE_PNG,
        )
    }

    fun downloadFile(url: String): File = runBlocking(Dispatchers.IO) {

        var contentType: MediaType = MediaType.ALL
        val fileByteArray = WebClient.create(url)
            .get()
            .awaitExchange { response ->
                contentType = response.headers().contentType().get()
                response.awaitBody<ByteArray>()
            }
        if (!ImageTypes.contains(contentType)) {
            throw InvalidMediaTypeException(contentType.type, "Only image mimetype allowed")
        }
        Files.write(Path.of("${System.currentTimeMillis()}.${contentType.subtype}"), fileByteArray).toFile()
    }
}