package pl.lunchbuddy.server.user.api

import pl.lunchbuddy.server.common.CommandHandler
import pl.lunchbuddy.server.common.EventBus
import pl.lunchbuddy.server.user.domain.User


class CreateUserCmdHandler(private var repository: UserRepository) : CommandHandler<CreateUserCmd, UserCreatedEvent> {

    override fun getClazz(): Class<CreateUserCmd> {
        return CreateUserCmd::class.java
    }

    override fun execute(command: CreateUserCmd): UserCreatedEvent {
        val user = User(command.name)

        repository.save(user)

        return UserCreatedEvent(userId = user.id)
    }
}