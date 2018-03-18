package pl.lunchbuddy.server.group.api

import pl.lunchbuddy.server.common.Command


data class AddGroupMemberCmd(val groupId: String, val userId: String) : Command
