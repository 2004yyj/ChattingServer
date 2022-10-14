package com.chatting.server.domain.mapper

import com.chatting.server.domain.dto.RoomDTO
import com.chatting.server.domain.dto.RoomDetailDTO
import com.chatting.server.domain.entity.Message
import com.chatting.server.domain.entity.Room

fun Room.toListDTO() = RoomDTO(
    this@toListDTO.id,
    this@toListDTO.roomName,
    this@toListDTO.creator.name,
    this@toListDTO.subscriber.name
)

fun Room.toDetailDTO() = RoomDetailDTO(
    this@toDetailDTO.id,
    this@toDetailDTO.roomName,
    this@toDetailDTO.creator.name,
    this@toDetailDTO.subscriber.name,
    this@toDetailDTO.messages.map(Message::toListDTO)
)