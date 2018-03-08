package pl.lunchbuddy.server.group.api

import pl.lunchbuddy.server.group.domain.Group
import pl.lunchbuddy.server.user.api.UserApi
import java.util.logging.Logger


class GroupApi(private var repository: GroupRepository, private var userApi: UserApi) {

    private val LOG: Logger = Logger.getLogger(javaClass.canonicalName)

    fun addGroup(dto: GroupDto): String {

        val creator = userApi.getUser(dto.creatorId)
                ?: throw IllegalStateException("No user found with provided id : " + dto.creatorId + ", could not create group ")

        val group = Group(dto.name, creator, emptySet())

        repository.save(group)

        LOG.info("Group created : " + group)

        return group.code
    }

    fun getAllGroups(): Collection<Group> {
        return repository.findAll()
    }

    fun getGroup(id: String): Group {
        return repository.findById(id) ?: throw IllegalArgumentException("No group found with provided id : " + id)
    }


}

