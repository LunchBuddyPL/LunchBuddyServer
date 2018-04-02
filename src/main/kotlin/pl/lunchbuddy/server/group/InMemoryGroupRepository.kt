package pl.lunchbuddy.server.group

import pl.lunchbuddy.server.group.api.GroupRepository
import pl.lunchbuddy.server.group.domain.Group
import pl.lunchbuddy.server.group.domain.GroupCode
import pl.lunchbuddy.server.group.domain.GroupId


class InMemoryGroupRepository : GroupRepository {

    private var cache: MutableMap<GroupId, Group> = mutableMapOf()

    override fun findAll(): Collection<Group> {
        return cache.values
    }

    override fun find(id: GroupId): Group? {
        return cache[id]
    }

    override fun findByCode(id: GroupCode): Group? {
        return cache.values.find { group -> group.code == id }
    }

    override fun save(group: Group) {
        cache[group.id] = group
    }

}