package com.chatting.server.repository

import com.chatting.server.domain.entity.Message
import com.chatting.server.domain.entity.Room
import org.springframework.data.jpa.repository.JpaRepository

interface MessageRepository: JpaRepository<Message, Long> {
    fun findAllByRoom(room: Room): List<Message>
}