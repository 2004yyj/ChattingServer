package com.chatting.server.service.message

import com.chatting.server.domain.dto.MessageDTO
import com.chatting.server.domain.entity.Room
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.socket.WebSocketSession

interface MessageService {
    fun handleActions(webSocketSession: WebSocketSession, messageDTO: MessageDTO)
    fun saveMessage(room: Room, messageDTO: MessageDTO)
    fun sendMessage(messageDTO: MessageDTO)
}
