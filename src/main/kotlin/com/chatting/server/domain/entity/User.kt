package com.chatting.server.domain.entity

import javax.persistence.*

@Entity
@Table(
    name = "user",
    uniqueConstraints = [UniqueConstraint(columnNames = ["name"])]
)
class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    var id: Long = 0

    var name: String = ""
}