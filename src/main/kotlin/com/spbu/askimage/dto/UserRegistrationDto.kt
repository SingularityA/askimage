package com.spbu.askimage.dto

import com.spbu.askimage.validator.FieldMatch
import javax.validation.constraints.NotEmpty

@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
class UserRegistrationDto {

    @NotEmpty
    var userName: String? = null

    @NotEmpty
    var password: String? = null

    @NotEmpty
    var confirmPassword: String? = null
}