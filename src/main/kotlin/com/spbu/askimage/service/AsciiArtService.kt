package com.spbu.askimage.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.TreeMap
import java.awt.image.BufferedImage
import java.awt.image.RasterFormatException

@Service
class AsciiArtService {

    @Value("#{\${brightnessMap}}")
    private lateinit var asciiBrightnessMap: TreeMap<Int, String>

    fun processImage(image: BufferedImage, outputWidth: Int): String {

        val builder = StringBuilder()

        val outputHeight = outputWidth * 3 / 5
        val chunkWidth = image.width / outputWidth
        val chunkHeight = image.height / outputHeight

        var chunk: BufferedImage
        for (y in 0 until outputHeight) {
            for (x in 0 until outputWidth) {
                try {
                    chunk = image.getSubimage(x * chunkWidth, y * chunkHeight, chunkHeight, chunkWidth)
                    builder.append(convertChunkToAscii(chunk))
                } catch (e: RasterFormatException) {
                }
            }
            builder.append("\n")
        }

        return builder.toString()
    }

    private fun convertChunkToAscii(chunk: BufferedImage): String {
        val width = chunk.width
        val height = chunk.height
        var pixel: Int
        var red: Int
        var green: Int
        var blue: Int
        var sum = 0
        for (x in 0 until width) {
            for (y in 0 until height) {
                pixel = chunk.getRGB(x, y)
                red = 0xff and (pixel shr 16)
                green = 0xff and (pixel shr 8)
                blue = 0xff and pixel
                sum += (0.33 * red + 0.56 * green + 0.11 * blue).toInt()
            }
        }
        val average = sum / (width * height)
        return asciiBrightnessMap.floorEntry(average).value
    }
}