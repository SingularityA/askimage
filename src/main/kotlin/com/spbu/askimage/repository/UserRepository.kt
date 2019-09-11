package com.spbu.askimage.repository

import com.spbu.askimage.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByUserName(userName: String?): User?
}