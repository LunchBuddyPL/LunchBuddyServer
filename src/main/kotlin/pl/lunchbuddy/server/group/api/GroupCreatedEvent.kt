package pl.lunchbuddy.server.group.api

import pl.lunchbuddy.server.common.Event


data class GroupCreatedEvent(val groupId: String) : Event
