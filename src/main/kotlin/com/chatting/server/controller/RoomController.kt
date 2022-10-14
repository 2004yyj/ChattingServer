package com.chatting.server.controller

import com.chatting.server.domain.dto.CreateRoomDTO
import com.chatting.server.domain.dto.RoomDTO
import com.chatting.server.domain.dto.RoomDetailDTO
import com.chatting.server.domain.dto.UpdateRoomDTO
import com.chatting.server.service.room.RoomService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/room")
class RoomController(
    private val roomService: RoomService
) {
    @GetMapping("/list/{userName}")
    fun getAllByUserId(@PathVariable userName: String): ResponseEntity<List<RoomDTO>> {
        val data = roomService.findAllByUserId(userName)
        return ResponseEntity(data, HttpStatus.OK)
    }

    @GetMapping("/{roomId}")
    fun getRoomDetail(@PathVariable roomId: Long): ResponseEntity<RoomDetailDTO> {
        val data = roomService.findDetailRoom(roomId)
        return ResponseEntity(data, HttpStatus.OK)
    }

    @PostMapping
    fun postRoom(
        @RequestBody createRoomDTO: CreateRoomDTO
    ): ResponseEntity<Nothing> {
        roomService.saveRoom(createRoomDTO)
        return ResponseEntity(null, HttpStatus.OK)
    }

    @PatchMapping
    fun updateRoom(
        @RequestBody updateRoomDTO: UpdateRoomDTO
    ): ResponseEntity<Nothing> {
        roomService.updateRoomName(updateRoomDTO)
        return ResponseEntity(null, HttpStatus.OK)
    }
}