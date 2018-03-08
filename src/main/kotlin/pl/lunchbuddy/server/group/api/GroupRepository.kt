package pl.lunchbuddy.server.group.api

import pl.lunchbuddy.server.group.domain.Group


interface GroupRepository {

    fun findAll() : Collection<Group>

    fun findById(id: String) : Group?

    fun save(group: Group)

}