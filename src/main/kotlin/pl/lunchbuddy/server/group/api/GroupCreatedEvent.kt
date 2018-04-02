package pl.lunchbuddy.server.group.api

import pl.lunchbuddy.server.common.Event
import pl.lunchbuddy.server.group.domain.GroupCode
import pl.lunchbuddy.server.group.domain.GroupId


data class GroupCreatedEvent(val groupId: GroupId, val groupCode: GroupCode) : Event
