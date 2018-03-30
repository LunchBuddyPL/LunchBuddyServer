package pl.lunchbuddy.server.common

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct


@Component
class SyncCommandDispatcher : CommandDispatcher {

    private val log: Logger = LoggerFactory.getLogger(javaClass.canonicalName)
    private val handlersMap: Map<Class<*>, CommandHandler<Command, *>>

    constructor(handlers: List<CommandHandler<*, *>>) {
        //TODO Kotlin generics!! agrrr - why List<CommandHandler<Command,*>> is not being injected?
        handlersMap = (handlers as List<CommandHandler<Command, *>>).associateBy({ it.getClazz() }, { it })
    }


    override fun <T : Command> handle(command: T): CommandResult {
        return handlersMap[command::class.java]?.execute(command)
                ?: throw IllegalStateException("No handler for class : $command")
    }

    @PostConstruct
    private fun listHandlers() {
        handlersMap.forEach { t, u -> log.debug("Registered handler for : $t -> $u") }
    }

}