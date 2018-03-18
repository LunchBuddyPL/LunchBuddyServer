package pl.lunchbuddy.server.group.api

import pl.lunchbuddy.server.common.Command
import java.time.LocalTime


data class CreateGroupCmd(val name: String, val creatorId: String, val start: LocalTime, val end: LocalTime) : Command
