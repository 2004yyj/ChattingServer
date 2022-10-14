package com.chatting.server.repository

import com.chatting.server.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository: JpaRepository<User, Long> {
    fun findByName(name: String): Optional<User>
}