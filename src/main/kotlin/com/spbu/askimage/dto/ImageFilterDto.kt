package com.spbu.askimage.dto

import org.springframework.web.multipart.MultipartFile
import javax.validation.constraints.NotNull

class ImageFilterDto {

    @NotNull
    var file: MultipartFile? = null

    var scale: String? = null

    var color: Boolean? = null

    override fun toString(): String {
        return "ImageFilterDto(file=$file, scale=$scale, color=$color)"
    }
}