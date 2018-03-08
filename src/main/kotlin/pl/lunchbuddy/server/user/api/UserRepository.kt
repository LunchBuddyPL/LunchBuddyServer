package pl.lunchbuddy.server.user.api

import pl.lunchbuddy.server.user.domain.User


interface UserRepository {

    fun findById(id: String): User?

    fun save(user: User)
}