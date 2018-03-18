package pl.lunchbuddy.server.user.api

import pl.lunchbuddy.server.common.Event


data class UserCreatedEvent(val userId: String) : Event
