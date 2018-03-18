package pl.lunchbuddy.server.user.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import pl.lunchbuddy.server.common.CommandDispatcher
import pl.lunchbuddy.server.user.domain.User


@RestController
@RequestMapping("/user")
class UserEndpoint(private var userApi: UserApi, private var commandDispatcher: CommandDispatcher) {

    @GetMapping("/{id}")
    fun getUser(@PathVariable(value = "id") id: String): User? {
        return userApi.getUser(id)
    }

    @PostMapping
    fun addUser(@RequestBody command: CreateUserCmd): ResponseEntity<String> {
        val userId = commandDispatcher.handle(command)

        val location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(userId)
                .toUri()

        return ResponseEntity.created(location).body(userId.toString())


    }

}
