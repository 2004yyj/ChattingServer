package com.chatting.server.domain.dto

data class MessageDTO(
    val roomId: Long,
    val sender: String,
    val text: String
)