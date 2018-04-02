package pl.lunchbuddy.server.group.api

import org.springframework.hateoas.Link
import org.springframework.hateoas.Resource
import org.springframework.hateoas.Resources
import org.springframework.hateoas.mvc.ControllerLinkBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import pl.lunchbuddy.server.common.CommandDispatcher
import pl.lunchbuddy.server.group.domain.Group
import pl.lunchbuddy.server.group.domain.GroupId


@RestController
@RequestMapping("/group")
class GroupEndpoint(private var groupApi: GroupApi, private var commandDispatcher: CommandDispatcher) {

    @GetMapping("/{id}")
    fun getGroup(@PathVariable(value = "id") id: String): ResponseEntity<Resource<Group>> {
        val group = groupApi.getGroup(GroupId(id))
        return ResponseEntity.ok(Resource(group, groupLink(group)))
    }


    @GetMapping()
    fun getAllGroups(): ResponseEntity<Resources<Resource<Group>>> {
        val allGroups = groupApi.getAllGroups()
        return ResponseEntity.ok().body(Resources(allGroups.map { group -> Resource(group, groupLink(group)) }))
    }

    @PostMapping
    fun addGroup(@RequestBody cmd: CreateGroupCmd): ResponseEntity<GroupCreatedDto> {
        val group = commandDispatcher.handle(cmd) as GroupCreatedEvent
        val location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(group.groupId).toUri()

        return ResponseEntity.created(location).body(GroupCreatedDto(group.groupId.toString(), group.groupCode.toString()))
    }


    @PutMapping("/member")
    fun addMember(@RequestBody cmd: AddGroupMemberCmd): ResponseEntity<Void> {
        commandDispatcher.handle(cmd)
        return ResponseEntity.accepted().build()
    }

    private fun groupLink(group: Group): Collection<Link> {
        return setOf(
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(GroupEndpoint::class.java).getGroup(group.id.toString())).withSelfRel()
        )
    }

}
