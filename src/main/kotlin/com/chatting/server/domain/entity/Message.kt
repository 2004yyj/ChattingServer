package com.chatting.server.domain.entity

import javax.persistence.*

@Entity
@Table(name = "message")
class Message {

    @Id
    @GeneratedValue
    @Column(name = "message_id")
    var id: Long = 0

    @ManyToOne
    @JoinColumn(name = "room_id")
    var room: Room = Room()

    @ManyToOne
    @JoinColumn(name = "user_id")
    var sender: User = User()

    var text: String = ""
}