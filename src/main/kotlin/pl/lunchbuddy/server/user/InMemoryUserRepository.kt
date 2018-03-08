package pl.lunchbuddy.server.user

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import pl.lunchbuddy.server.user.domain.User
import pl.lunchbuddy.server.user.api.UserRepository


class InMemoryUserRepository : UserRepository {

    var cache: Cache<String, User> = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .build()

    override fun findById(id: String): User? {
        return cache.getIfPresent(id)
    }

    override fun save(user: User) {
        cache.put(user.id, user)
    }

}