package com.chatting.server.domain.dto

data class RoomDTO(
    val id: Long,
    val roomName: String,
    val creator: String,
    val subscriber: String,
)