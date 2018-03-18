package pl.lunchbuddy.server.common


interface EventBus {

    fun <T : Event> handle(event: T)
}