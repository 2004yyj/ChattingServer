package com.chatting.server.domain.mapper

import com.chatting.server.domain.dto.UserDTO
import com.chatting.server.domain.entity.User

fun User.toDTO() = UserDTO(this.id, this.name)