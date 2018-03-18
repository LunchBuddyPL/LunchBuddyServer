package pl.lunchbuddy.server.common

import org.springframework.stereotype.Component


@Component
class InMemoryEventBus : EventBus {


    override fun <T : Event> handle(event: T) {
        print("New event registered : $event")
    }


}