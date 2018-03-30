package pl.lunchbuddy.server.group.api

import pl.lunchbuddy.server.common.CommandHandler
import pl.lunchbuddy.server.user.api.UserApi


class AddGroupMemberCmdHandler(private var repository: GroupRepository, private var userApi: UserApi) : CommandHandler<AddGroupMemberCmd, GroupMemberAddedEvent> {

    override fun getClazz(): Class<AddGroupMemberCmd> {
        return AddGroupMemberCmd::class.java
    }

    override fun execute(command: AddGroupMemberCmd): GroupMemberAddedEvent {

        val user = userApi.getUser(command.userId)

        val group = repository.findById(command.groupId) ?: throw GroupNotFoundException.withId(command.groupId)

        group.addMember(user)

        repository.save(group)

        return GroupMemberAddedEvent(command.groupId, command.userId)
    }
}