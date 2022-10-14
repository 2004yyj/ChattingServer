package com.chatting.server.domain.dto

data class RoomDetailDTO(
    val id: Long,
    val roomName: String,
    val creator: String,
    val subscriber: String,
    val messages: List<MessageDTO>
)