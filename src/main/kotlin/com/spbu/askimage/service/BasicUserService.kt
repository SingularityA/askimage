package com.spbu.askimage.service

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.GrantedAuthority
import com.spbu.askimage.dto.UserRegistrationDto
import com.spbu.askimage.entity.Role
import com.spbu.askimage.entity.User
import com.spbu.askimage.repository.RoleRepository
import com.spbu.askimage.repository.UserRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import kotlin.streams.toList

@Service
class BasicUserService : UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var roleRepository: RoleRepository

    @Autowired
    private lateinit var passwordEncoder: BCryptPasswordEncoder

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(userName: String): UserDetails {
        val user = userRepository.findByUserName(userName)
                ?: throw UsernameNotFoundException("Invalid username or password.")

        return org.springframework.security.core.userdetails.User(
                user.userName,
                user.password,
                mapRolesToAuthorities(user.roles)
        )
    }

    override fun findById(id: Long): User? {
        return userRepository.findByIdOrNull(id);
    }

    override fun findByUserName(userName: String?): User? {
        return userRepository.findByUserName(userName)
    }

    override fun save(userDto: UserRegistrationDto): User? {
        val user = User()
        user.userName = userDto.userName
        user.password = passwordEncoder.encode(userDto.password)
        user.roles = listOf(roleRepository.findByName("USER") ?: throw RuntimeException("No such role"))
        return userRepository.save(user)
    }

    private fun mapRolesToAuthorities(roles: Collection<Role>): Collection<GrantedAuthority> {
        return roles.stream()
                .map { role -> SimpleGrantedAuthority(role.name) }
                .toList()
    }
}