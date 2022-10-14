package com.chatting.server.service.room

import com.chatting.server.domain.dto.CreateRoomDTO
import com.chatting.server.domain.dto.RoomDTO
import com.chatting.server.domain.dto.RoomDetailDTO
import com.chatting.server.domain.dto.UpdateRoomDTO
import com.chatting.server.domain.entity.Room
import com.chatting.server.domain.mapper.toDetailDTO
import com.chatting.server.domain.mapper.toListDTO
import com.chatting.server.repository.RoomRepository
import com.chatting.server.service.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class RoomServiceImpl(
    private val roomRepository: RoomRepository,
    private val userService: UserService
): RoomService {
    @Transactional(readOnly = true)
    override fun findAllByUserId(userName: String): List<RoomDTO> {
        val user = userService.findByName(userName)
        return roomRepository.findAllByUserId(user.id).map(Room::toListDTO)
    }

    override fun findDetailRoom(roomId: Long): RoomDetailDTO {
        return findById(roomId).toDetailDTO()
    }

    override fun findById(roomId: Long): Room {
        return roomRepository.findById(roomId).orElseThrow {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "방을 찾을 수 없습니다.")
        }
    }

    @Transactional
    override fun updateRoomName(updateRoomDTO: UpdateRoomDTO) {
        try {
            val room = findById(updateRoomDTO.id).apply {
                this.roomName = updateRoomDTO.roomName
            }
            roomRepository.save(room)
        } catch (e: Exception) {
            throw e
        }
    }

    @Transactional
    override fun saveRoom(createRoomDTO: CreateRoomDTO) {
        try {
            val room = Room().apply {
                this.roomName = createRoomDTO.roomName
                this.creator = userService.findByName(createRoomDTO.creator)
                this.subscriber = userService.findByName(createRoomDTO.subscriber)
            }
            roomRepository.save(room)
        } catch (e: Exception) {
            throw e
        }
    }
}