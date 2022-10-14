package com.chatting.server.domain.entity

import javax.persistence.*

@Entity
@Table(name = "room")
class Room {
    @Id
    @GeneratedValue
    @Column(name = "room_id")
    var id: Long = 0

    var roomName: String = ""

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false, referencedColumnName = "user_id")
    var creator: User = User()

    @ManyToOne
    @JoinColumn(name = "subscriber_id", nullable = false, referencedColumnName = "user_id")
    var subscriber: User = User()

    @OneToMany(mappedBy = "room")
    var messages: MutableList<Message> = mutableListOf()
}