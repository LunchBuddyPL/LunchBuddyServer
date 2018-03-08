package pl.lunchbuddy.server.group.api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.lunchbuddy.server.group.InMemoryGroupRepository
import pl.lunchbuddy.server.user.api.UserApi


@Configuration
class GroupModuleConfig {

    @Bean
    fun groupApi(userApi: UserApi): GroupApi {
        return GroupApi(InMemoryGroupRepository(), userApi)
    }

}