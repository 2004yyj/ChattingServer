package com.chatting.server.service.user

import com.chatting.server.domain.dto.RegisterDTO
import com.chatting.server.domain.dto.UpdateUserDTO
import com.chatting.server.domain.dto.UserDTO
import com.chatting.server.domain.entity.User
import com.chatting.server.domain.mapper.toDTO
import com.chatting.server.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import javax.transaction.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {
    override fun findByName(name: String): User {
        return userRepository.findByName(name).orElseThrow {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.")
        }
    }

    override fun findById(userId: Long): User {
        return userRepository.findById(userId).orElseThrow {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.")
        }
    }

    override fun findAll(): List<UserDTO> {
        return userRepository.findAll().map(User::toDTO)
    }

    private fun validateDuplicateUser(name: String) {
        userRepository.findByName(name).ifPresent {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 존재하는 계정")
        }
    }

    @Transactional
    override fun saveUser(registerDTO: RegisterDTO) {
        try {
            validateDuplicateUser(registerDTO.name)
            val user = User().apply {
                this.name = registerDTO.name
            }
            userRepository.save(user)
        } catch (e: Exception) {
            throw e
        }
    }

    @Transactional
    override fun updateUser(updateUserDTO: UpdateUserDTO) {
        try {
            val user = findById(updateUserDTO.id).apply {
                this.name = updateUserDTO.name
            }
            userRepository.save(user)
        } catch (e: Exception) {
            throw e
        }
    }
}