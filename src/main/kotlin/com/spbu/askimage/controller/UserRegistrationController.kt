package com.spbu.askimage.controller

import com.spbu.askimage.dto.UserRegistrationDto
import com.spbu.askimage.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Controller
@RequestMapping("/register")
open class UserRegistrationController {

    @Autowired
    private lateinit var userService: UserService

    @ModelAttribute("user")
    fun userRegistrationDto(): UserRegistrationDto {
        return UserRegistrationDto()
    }

    @GetMapping
    fun getRegisterPage() = "register"

    @PostMapping
    fun registerUserAccount(@ModelAttribute("user") @Valid userDto: UserRegistrationDto,
                            result: BindingResult): String {

        val existing = userService.findByUserName(userDto.userName)
        if (existing != null) {
            result.rejectValue("userName", "", "There is already an account registered with that name")
        }

        if (result.hasErrors()) {
            return "register"
        }

        userService.save(userDto)
        return "redirect:/register?success"
    }
}