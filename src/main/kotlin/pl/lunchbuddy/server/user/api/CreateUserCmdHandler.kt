package pl.lunchbuddy.server.user.api

import pl.lunchbuddy.server.common.CommandHandler
import pl.lunchbuddy.server.common.EventBus
import pl.lunchbuddy.server.user.domain.User


class CreateUserCmdHandler(private var repository: UserRepository, private var eventBus: EventBus) : CommandHandler<CreateUserCmd, String> {

    override fun getClazz(): Class<CreateUserCmd> {
        return CreateUserCmd::class.java
    }

    override fun execute(command: CreateUserCmd): String {
        val user = User(command.name)

        repository.save(user)

        eventBus.handle(UserCreatedEvent(userId = user.id))

        return user.id
    }
}