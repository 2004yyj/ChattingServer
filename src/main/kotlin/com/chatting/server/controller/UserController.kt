package com.chatting.server.controller

import com.chatting.server.domain.dto.RegisterDTO
import com.chatting.server.domain.dto.UpdateUserDTO
import com.chatting.server.domain.dto.UserDTO
import com.chatting.server.service.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/list")
    fun getAll(): ResponseEntity<List<UserDTO>> {
        val data = userService.findAll()
        return ResponseEntity(data, HttpStatus.OK)
    }

    @PostMapping("/{name}")
    fun postUser(
        @RequestBody registerDTO: RegisterDTO
    ): ResponseEntity<Nothing> {
        userService.saveUser(registerDTO)
        return ResponseEntity(null, HttpStatus.OK)
    }

    @PatchMapping("/{name}")
    private fun updateUser(
        @RequestBody updateUserDTO: UpdateUserDTO
    ): ResponseEntity<Nothing> {
        userService.updateUser(updateUserDTO)
        return ResponseEntity(null, HttpStatus.OK)
    }
}