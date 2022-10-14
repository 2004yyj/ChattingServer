package com.chatting.server.domain.mapper

import com.chatting.server.domain.dto.MessageDTO
import com.chatting.server.domain.entity.Message

fun Message.toListDTO() = MessageDTO(
    this.room.id,
    this.sender.name,
    this.text
)