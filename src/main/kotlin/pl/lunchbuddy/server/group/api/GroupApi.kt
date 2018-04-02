package pl.lunchbuddy.server.group.api

import pl.lunchbuddy.server.group.domain.Group
import pl.lunchbuddy.server.group.domain.GroupCode
import pl.lunchbuddy.server.group.domain.GroupId
import java.util.logging.Logger


class GroupApi(private var repository: GroupRepository) {

    private val LOG: Logger = Logger.getLogger(javaClass.canonicalName)

    fun getAllGroups(): Collection<Group> {
        return repository.findAll()
    }

    fun getGroup(id: GroupId): Group {
        return repository.find(id) ?: throw IllegalArgumentException("No group found with provided id : $id ")
    }

}

