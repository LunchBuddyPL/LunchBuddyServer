package pl.lunchbuddy.server.group.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import pl.lunchbuddy.server.group.domain.Group


@RestController
@RequestMapping("/group")
class GroupEndpoint(private var groupApi: GroupApi) {

    @GetMapping("/{id}")
    fun getGroup(@PathVariable(value = "id") id: String): Group? {
        return groupApi.getGroup(id)
    }

    @GetMapping()
    fun getAllGroups(): Collection<Group> {
        return groupApi.getAllGroups()
    }

    @PostMapping
    fun addGroup(@RequestBody groupDto: GroupDto): ResponseEntity<String> {
        val group = groupApi.addGroup(groupDto)

        val location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(group).toUri()

        return ResponseEntity.created(location).body(group)


    }

}
