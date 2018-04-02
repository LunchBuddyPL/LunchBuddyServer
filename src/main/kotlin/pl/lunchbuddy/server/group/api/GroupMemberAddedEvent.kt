package pl.lunchbuddy.server.group.api

import pl.lunchbuddy.server.common.Event
import pl.lunchbuddy.server.group.domain.GroupId


data class GroupMemberAddedEvent(val groupId: GroupId, val userId: String) : Event
