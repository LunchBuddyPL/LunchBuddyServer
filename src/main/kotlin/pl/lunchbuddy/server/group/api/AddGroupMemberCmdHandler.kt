package pl.lunchbuddy.server.group.api

import pl.lunchbuddy.server.common.CommandHandler
import pl.lunchbuddy.server.group.domain.GroupCode
import pl.lunchbuddy.server.user.api.UserApi


class AddGroupMemberCmdHandler(private var repository: GroupRepository, private var userApi: UserApi) : CommandHandler<AddGroupMemberCmd, GroupMemberAddedEvent> {

    override fun getClazz(): Class<AddGroupMemberCmd> {
        return AddGroupMemberCmd::class.java
    }

    override fun execute(command: AddGroupMemberCmd): GroupMemberAddedEvent {

        val user = userApi.getUser(command.userId)

        val group = repository.findByCode(GroupCode(command.groupCode))
                ?: throw GroupNotFoundException.withCode(command.groupCode)

        group.addMember(user)

        repository.save(group)

        return GroupMemberAddedEvent(group.id, command.userId)
    }
}