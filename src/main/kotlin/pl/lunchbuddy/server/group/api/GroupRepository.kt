package pl.lunchbuddy.server.group.api

import pl.lunchbuddy.server.group.domain.Group
import pl.lunchbuddy.server.group.domain.GroupCode
import pl.lunchbuddy.server.group.domain.GroupId


interface GroupRepository {

    fun findAll() : Collection<Group>

    fun find(id: GroupId) : Group?

    fun findByCode(id: GroupCode) : Group?

    fun save(group: Group)

}