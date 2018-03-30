package pl.lunchbuddy.server.group.api

import pl.lunchbuddy.server.common.CommandHandler
import pl.lunchbuddy.server.group.domain.Group
import pl.lunchbuddy.server.group.domain.MealOption
import pl.lunchbuddy.server.group.domain.MealTime
import pl.lunchbuddy.server.user.api.UserApi


class CreateGroupCmdHandler(private var repository: GroupRepository, private var userApi: UserApi) : CommandHandler<CreateGroupCmd, GroupCreatedEvent> {

    override fun getClazz(): Class<CreateGroupCmd> {
        return CreateGroupCmd::class.java
    }

    override fun execute(command: CreateGroupCmd): GroupCreatedEvent {

        val creator = userApi.getUser(command.creatorId)

        val group = Group(command.name, MealTime(command.start, command.end), creator, hashSetOf(MealOption("default")))

        repository.save(group)

        return GroupCreatedEvent(groupId = group.code)
    }
}