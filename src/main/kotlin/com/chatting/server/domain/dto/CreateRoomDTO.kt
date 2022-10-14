package com.chatting.server.domain.dto

data class CreateRoomDTO(
    val roomName: String,
    val creator: String,
    val subscriber: String,
)