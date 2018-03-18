package pl.lunchbuddy.server.user.api

import pl.lunchbuddy.server.common.Command


data class CreateUserCmd(val name: String) : Command
