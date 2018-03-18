package pl.lunchbuddy.server.group.api

import pl.lunchbuddy.server.common.CommandHandler
import pl.lunchbuddy.server.common.EventBus
import pl.lunchbuddy.server.user.api.UserApi


class AddGroupMemberCmdHandler(private var repository: GroupRepository, private var userApi: UserApi, private var eventBus: EventBus) : CommandHandler<AddGroupMemberCmd, Nothing?> {

    override fun getClazz(): Class<AddGroupMemberCmd> {
        return AddGroupMemberCmd::class.java
    }

    override fun execute(command: AddGroupMemberCmd): Nothing? {

        val user = userApi.getUser(command.userId)

        val group = repository.findById(command.groupId) ?: throw GroupNotFoundException.withId(command.groupId)

        group.addMember(user)

        repository.save(group)

        eventBus.handle(GroupMemberAddedEvent(command.groupId, command.userId))

        return null
    }
}