package pl.lunchbuddy.server.common


interface CommandDispatcher {

    fun <T : Command> handle(command: T) : CommandResult
}