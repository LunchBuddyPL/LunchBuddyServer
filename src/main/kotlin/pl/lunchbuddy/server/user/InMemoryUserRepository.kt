package pl.lunchbuddy.server.user

import pl.lunchbuddy.server.user.api.UserRepository
import pl.lunchbuddy.server.user.domain.User


class InMemoryUserRepository : UserRepository {

    private var cache: MutableMap<String, User> = mutableMapOf()


    override fun findById(id: String): User? {
        return cache[id]
    }

    override fun save(user: User) {
        cache[user.id] = user
    }

}