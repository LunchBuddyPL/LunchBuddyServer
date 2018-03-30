package pl.lunchbuddy.server.user.domain

import java.time.LocalDateTime
import java.util.*


open class User(val name: String) {

    val id: String = UUID.randomUUID().toString()
    val creationDate: LocalDateTime = LocalDateTime.now()

    override fun toString(): String {
        return "User(name='$name', id='$id', creationDate=$creationDate)"
    }

}