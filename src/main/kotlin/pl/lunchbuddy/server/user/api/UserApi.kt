package pl.lunchbuddy.server.user.api

import pl.lunchbuddy.server.user.domain.User
import java.util.logging.Logger


class UserApi(private var repository: UserRepository) {

    private val LOG: Logger = Logger.getLogger(javaClass.canonicalName)


    fun getUser(id: String): User {
        return repository.findById(id) ?: throw UserNotFoundException.withId(id)
    }

}