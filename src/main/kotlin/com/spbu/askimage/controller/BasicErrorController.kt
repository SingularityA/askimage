package com.spbu.askimage.controller

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.http.HttpStatus
import org.springframework.ui.Model
import org.springframework.ui.set
import javax.servlet.RequestDispatcher
import javax.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class BasicErrorController : ErrorController {

    override fun getErrorPath() = "/error"

    @RequestMapping("/error")
    fun handleError(request: HttpServletRequest, model: Model): String {
        val status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE) ?: return "error"
        val code = HttpStatus.valueOf(Integer.valueOf(status.toString()))

        model["code"] = code.value()
        model["message"] = when (code) {
            HttpStatus.NOT_FOUND -> "It seems that page or resource does not exist"
            HttpStatus.FORBIDDEN -> "Meow... it is forbidden resource"
            HttpStatus.INTERNAL_SERVER_ERROR -> "Ooops! Our fault. Plz, try again later"
            else -> "Unexpected error happened. We do not know, what it is"
        }
        return "error"
    }
}