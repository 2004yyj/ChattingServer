package com.chatting.server.repository

import com.chatting.server.domain.entity.Room
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface RoomRepository: JpaRepository<Room, Long> {
    @Query("SELECT * FROM Room WHERE creator_id = :user or subscriber_id = :user", nativeQuery = true)
    fun findAllByUserId(@Param("user") userId: Long): List<Room>

    fun findByRoomName(roomName: String): Optional<Room>
}