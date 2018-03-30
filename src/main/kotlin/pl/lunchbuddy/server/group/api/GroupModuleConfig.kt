package pl.lunchbuddy.server.group.api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.lunchbuddy.server.group.InMemoryGroupRepository
import pl.lunchbuddy.server.user.api.UserApi


@Configuration
class GroupModuleConfig {

    @Bean
    fun groupApi(repository: GroupRepository): GroupApi {
        return GroupApi(repository)
    }

    @Bean
    fun createGroupCommandHandler(repository: GroupRepository, userApi: UserApi): CreateGroupCmdHandler {
        return CreateGroupCmdHandler(repository, userApi)
    }

    @Bean
    fun addGroupMemberCommandHandler(repository: GroupRepository, userApi: UserApi): AddGroupMemberCmdHandler {
        return AddGroupMemberCmdHandler(repository, userApi)
    }

    @Bean
    fun groupRepository(): GroupRepository {
        return InMemoryGroupRepository()
    }


}