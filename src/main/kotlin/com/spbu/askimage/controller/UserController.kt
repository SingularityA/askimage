package com.spbu.askimage.controller

import com.spbu.askimage.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/user")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/profile/{id}")
    fun getUserProfilePage(model: Model, @PathVariable id: Long): String {
        val user = userService.findById(id)
        model["user"] = user ?: return "home"
        model["message"] = "It is a nice day!"
        return "profile"
    }
}