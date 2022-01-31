package com.mayard.webpdownloader.handler

import com.mayard.webpdownloader.service.DownloadService
import com.mayard.webpdownloader.util.WebpConvertUtil
import org.springframework.http.InvalidMediaTypeException
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.queryParamOrNull
import kotlin.jvm.Throws

@Component
class MainHandler(
    private val downloadService: DownloadService
) {

    @Throws(InvalidMediaTypeException::class)
    suspend fun downloadImage(request: ServerRequest): ServerResponse {

        val imageURL = request.queryParamOrNull("url")
        val q = request.queryParamOrNull("q")

        val quality = q?.let {
            (q.toFloat() / 100F)
        } ?: let { 0.9F }

        imageURL?:let {
            return ServerResponse.badRequest().bodyValueAndAwait("url not found")
        }

        val originalFile = downloadService.downloadFile(imageURL)
        val webpFile = WebpConvertUtil.toWebp(originalFile, quality)
        return ServerResponse.ok().bodyValueAndAwait(webpFile)
    }

}