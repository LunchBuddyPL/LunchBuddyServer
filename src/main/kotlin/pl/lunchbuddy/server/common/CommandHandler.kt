package pl.lunchbuddy.server.common


interface CommandHandler<in T : Command, out X : Event > {

    //CommandHandler should not return anything...
    fun execute(command: T) : X

    fun getClazz(): Class<*>

}