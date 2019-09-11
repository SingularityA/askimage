package com.spbu.askimage.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class BasicController {

    @GetMapping("/")
    fun getRoot() = "redirect:home"

    @GetMapping("/home")
    fun getHomePage() = "home"

    @GetMapping("/login")
    fun getLoginPage() = "login"

    @GetMapping("/admin")
    fun getAdminPage() = "admin"
}