package com.chatting.server.handler

import com.chatting.server.domain.dto.MessageDTO
import com.chatting.server.service.message.MessageService
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler


@Component
class ChattingWebSocketHandler(
    private val messageService: MessageService
): TextWebSocketHandler() {
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val jacksonMessageConverter = MappingJackson2HttpMessageConverter()
        val objectMapper = jacksonMessageConverter.objectMapper

        val payload = message.payload

        val messageDTO = objectMapper.readValue<MessageDTO>(payload)
        messageService.handleActions(session, messageDTO)
    }
}