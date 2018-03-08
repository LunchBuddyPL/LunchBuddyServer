package pl.lunchbuddy.server.group

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import pl.lunchbuddy.server.group.api.GroupRepository
import pl.lunchbuddy.server.group.domain.Group


class InMemoryGroupRepository : GroupRepository {

    var cache: Cache<String, Group> = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .build()

    override fun findAll(): Collection<Group> {
        return cache.asMap().values
    }

    override fun findById(id: String): Group? {
        return cache.getIfPresent(id)
    }

    override fun save(group: Group) {
        cache.put(group.code, group)
    }

}