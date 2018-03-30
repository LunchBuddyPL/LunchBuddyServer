package pl.lunchbuddy.server.user.api

import org.springframework.hateoas.Resource
import org.springframework.hateoas.mvc.ControllerLinkBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import pl.lunchbuddy.server.common.CommandDispatcher
import pl.lunchbuddy.server.common.CommandResult
import pl.lunchbuddy.server.user.domain.User


@RestController
@RequestMapping("/user")
class UserEndpoint(private var userApi: UserApi, private var commandDispatcher: CommandDispatcher) {

    @GetMapping("/{id}")
    fun getUser(@PathVariable(value = "id") id: String): ResponseEntity<Resource<User>> {
        val user = userApi.getUser(id)
        val response = Resource(user, ControllerLinkBuilder.linkTo(ControllerLinkBuilder
                .methodOn(UserEndpoint::class.java)
                .getUser(user.id))
                .withSelfRel())

        return ResponseEntity.ok(response)
    }

    @PostMapping
    fun addUser(@RequestBody command: CreateUserCmd): ResponseEntity<CommandResult> {
        val user = commandDispatcher.handle(command)

        val userId = (user as UserCreatedEvent).userId

        val location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(userId)
                .toUri()

        return ResponseEntity.created(location).body(user)

    }

}
