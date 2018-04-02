package pl.lunchbuddy.server.common

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct


@Component
class SyncCommandDispatcher : CommandDispatcher {

    private val log: Logger = LoggerFactory.getLogger(javaClass.canonicalName)
    private val handlersMap: Map<Class<*>, CommandHandler<Command, *>>
    private val eventBus: EventBus

    constructor(handlers: List<CommandHandler<*, *>>, eventBus: EventBus) {
        //TODO Kotlin generics!! agrrr - why List<CommandHandler<Command,*>> is not being injected?
        this.handlersMap = (handlers as List<CommandHandler<Command, *>>).associateBy({ it.getClazz() }, { it })
        this.eventBus = eventBus
    }


    override fun <T : Command> handle(command: T): Event {
        val result = handlersMap[command::class.java]?.execute(command)
                ?: throw IllegalStateException("No handler for class : $command")

        eventBus.handle(result)

        return result
    }

    @PostConstruct
    private fun listHandlers() {
        handlersMap.forEach { t, u -> log.debug("Registered handler for : $t -> $u") }
    }

}