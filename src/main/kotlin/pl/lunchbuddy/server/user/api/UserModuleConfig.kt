package pl.lunchbuddy.server.user.api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.lunchbuddy.server.common.EventBus
import pl.lunchbuddy.server.user.InMemoryUserRepository


@Configuration
class UserModuleConfig {

    @Bean
    fun userApi(repository: UserRepository): UserApi {
        return UserApi(repository)
    }

    @Bean
    fun createUserCommandHandler(repository: UserRepository, eventBus: EventBus): CreateUserCmdHandler {
        return CreateUserCmdHandler(repository, eventBus)
    }

    @Bean
    fun userRepository(): UserRepository {
        return InMemoryUserRepository()
    }

}