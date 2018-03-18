package pl.lunchbuddy.server.group.api

import pl.lunchbuddy.server.common.Event


data class GroupMemberAddedEvent(val groupId: String, val userId: String) : Event
