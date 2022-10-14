package com.chatting.server.service.message

import com.chatting.server.domain.dto.MessageDTO
import com.chatting.server.domain.entity.Message
import com.chatting.server.domain.entity.Room
import com.chatting.server.repository.MessageRepository
import com.chatting.server.service.room.RoomService
import com.chatting.server.service.user.UserService
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession

@Service
class MessageServiceImpl(
    private val messageRepository: MessageRepository,
    private val roomService: RoomService,
    private val userService: UserService
): MessageService {
    private val sessions = HashSet<WebSocketSession>()

    @Transactional
    override fun handleActions(webSocketSession: WebSocketSession, messageDTO: MessageDTO) {
        try {
            val room = roomService.findById(messageDTO.roomId)
            val messages = messageRepository.findAllByRoom(room)

            if (
                !sessions.contains(webSocketSession) ||
                sessions.find { it.id == webSocketSession.id }?.isOpen == false
            ) {
                sessions.add(webSocketSession)
            }

            if (!messages.any { it.sender.name == messageDTO.sender }) {
                val welcomeMessageDTO = messageDTO.copy(text = "${messageDTO.sender}님이 입장했습니다.")
                saveMessage(room, welcomeMessageDTO)
            }
            saveMessage(room, messageDTO)
        } catch (e: Exception) {
            throw e
        }
    }

    @Transactional
    override fun saveMessage(room: Room, messageDTO: MessageDTO) {
        try {
            val message = Message().apply {
                this.text = messageDTO.text
                this.room = room
                this.sender = userService.findByName(messageDTO.sender)
            }
            messageRepository.save(message)
            sendMessage(messageDTO)
        } catch (e: Exception) {
            throw e
        }
    }

    override fun sendMessage(messageDTO: MessageDTO) {
        val jacksonMessageConverter = MappingJackson2HttpMessageConverter()
        val objectMapper = jacksonMessageConverter.objectMapper
        sessions.forEach { session ->
            if (session.isOpen) {
                session.sendMessage(
                    TextMessage(objectMapper.writeValueAsString(messageDTO))
                )
            }
        }
    }
}