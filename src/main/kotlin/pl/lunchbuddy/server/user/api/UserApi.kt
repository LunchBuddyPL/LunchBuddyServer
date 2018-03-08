package pl.lunchbuddy.server.user.api

import pl.lunchbuddy.server.user.domain.User
import java.util.logging.Logger


class UserApi(private var repository: UserRepository) {

    private val LOG: Logger = Logger.getLogger(javaClass.canonicalName);


    fun getUser(id: String): User? {
        return repository.findById(id)
    }

    fun addUser(userDto: UserDto): String {
        val user = User(userDto.name)
        repository.save(user)

        LOG.info("User created : " + user)
        return user.id
    }
}