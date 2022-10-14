package com.chatting.server.service.user

import com.chatting.server.domain.dto.RegisterDTO
import com.chatting.server.domain.dto.UpdateUserDTO
import com.chatting.server.domain.dto.UserDTO
import com.chatting.server.domain.entity.User

interface UserService {
    fun findByName(name: String): User
    fun findAll(): List<UserDTO>
    fun saveUser(registerDTO: RegisterDTO)
    fun findById(userId: Long): User
    fun updateUser(updateUserDTO: UpdateUserDTO)
}