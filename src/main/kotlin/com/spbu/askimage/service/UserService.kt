package com.spbu.askimage.service

import org.springframework.security.core.userdetails.UserDetailsService
import com.spbu.askimage.dto.UserRegistrationDto
import com.spbu.askimage.entity.User

interface UserService: UserDetailsService {

    fun findById(id: Long): User?

    fun findByUserName(userName: String?): User?

    fun save(userDto: UserRegistrationDto): User?
}