package pl.lunchbuddy.server.user.api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.lunchbuddy.server.user.InMemoryUserRepository


@Configuration
class UserModuleConfig {

    @Bean
    fun userApi(): UserApi {
        return UserApi(InMemoryUserRepository())
    }

}