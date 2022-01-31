package com.mayard.webpdownloader.util

import com.luciad.imageio.webp.WebPWriteParam
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.awt.color.ColorSpace
import java.awt.image.BufferedImage
import java.io.File
import java.util.*
import javax.imageio.IIOImage
import javax.imageio.ImageIO
import javax.imageio.ImageWriteParam
import javax.imageio.ImageWriter
import javax.imageio.stream.FileImageOutputStream

object WebpConvertUtil {

    fun toWebp(file: File, quality: Float): File = runBlocking(Dispatchers.IO) {

        val image: BufferedImage = ImageIO.read(file)

        val writer: ImageWriter = ImageIO.getImageWritersByMIMEType("image/webp").next()
        val writeParam = WebPWriteParam(Locale.ENGLISH)
        writeParam.compressionMode = ImageWriteParam.MODE_EXPLICIT
        writeParam.compressionType = writeParam.compressionTypes[WebPWriteParam.LOSSY_COMPRESSION]
        writeParam.compressionQuality = quality

        val webp = File("${System.currentTimeMillis()}.webp")
        writer.output = FileImageOutputStream(webp)

        if (image.colorModel.colorSpace.type == ColorSpace.TYPE_GRAY) {
            val rgbImage = BufferedImage(image.width, image.height, BufferedImage.TYPE_3BYTE_BGR)
            rgbImage.graphics.drawImage(image, 0, 0, null)
            writer.write(null, IIOImage(rgbImage, null, null), writeParam)
        } else {
            writer.write(null, IIOImage(image, null, null), writeParam)
        }
        webp
    }
}