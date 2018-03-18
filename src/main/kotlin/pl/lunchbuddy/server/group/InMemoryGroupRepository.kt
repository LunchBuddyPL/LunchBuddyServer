package pl.lunchbuddy.server.group

import pl.lunchbuddy.server.group.api.GroupRepository
import pl.lunchbuddy.server.group.domain.Group


class InMemoryGroupRepository : GroupRepository {

    private var cache: MutableMap<String, Group> = mutableMapOf()

    override fun findAll(): Collection<Group> {
        return cache.values
    }

    override fun findById(id: String): Group? {
        return cache[id]
    }

    override fun save(group: Group) {
        cache[group.code] = group
    }

}