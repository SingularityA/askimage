package com.spbu.askimage.controller

import com.spbu.askimage.dto.ImageFilterDto
import com.spbu.askimage.service.AsciiArtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import javax.validation.Valid

@Controller
@RequestMapping("/filter")
class FilterController {

    @Value("#{\${scaleMap}}")
    private lateinit var scaleMap: Map<String, Int>

    @Autowired
    private lateinit var artService: AsciiArtService

    @GetMapping("/ascii")
    fun getAsciiFilterPage(model: Model): String {
        model["imageDto"] = ImageFilterDto()
        model["textImage"] = ""
        return "filter"
    }

    @PostMapping("/ascii")
    fun submitImageFilterDto(model: Model,
                             @ModelAttribute("imageDto") @Valid dto: ImageFilterDto,
                             result: BindingResult): String {
        println(dto)
        if (dto.file?.isEmpty == true)
            result.rejectValue("file", "", "File should be specified!")
        if (result.hasErrors()) {
            return "filter"
        }

        val bufferedImage: BufferedImage = ImageIO.read(dto.file!!.inputStream)
        val resultTextImage = artService.processImage(bufferedImage, scaleMap[dto.scale]
                ?: scaleMap.getOrDefault("Simple", 70))
        println(result)
        model["textImage"] = resultTextImage
        return "filter"
    }
}