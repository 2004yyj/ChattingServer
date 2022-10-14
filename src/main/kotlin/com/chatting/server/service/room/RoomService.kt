package com.chatting.server.service.room

import com.chatting.server.domain.dto.CreateRoomDTO
import com.chatting.server.domain.dto.RoomDTO
import com.chatting.server.domain.dto.RoomDetailDTO
import com.chatting.server.domain.dto.UpdateRoomDTO
import com.chatting.server.domain.entity.Room

interface RoomService {
    fun findById(roomId: Long): Room
    fun updateRoomName(updateRoomDTO: UpdateRoomDTO)
    fun saveRoom(createRoomDTO: CreateRoomDTO)
    fun findDetailRoom(roomId: Long): RoomDetailDTO
    fun findAllByUserId(userName: String): List<RoomDTO>
}