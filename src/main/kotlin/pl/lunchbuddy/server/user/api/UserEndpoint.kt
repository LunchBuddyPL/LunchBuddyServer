package pl.lunchbuddy.server.user.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import pl.lunchbuddy.server.user.domain.User


@RestController
@RequestMapping("/user")
class UserEndpoint(var userApi: UserApi) {

    @GetMapping("/{id}")
    fun getUser(@PathVariable(value = "id") id: String): User? {
        return userApi.getUser(id)
    }

    @PostMapping
    fun addGroup(@RequestBody userDto: UserDto): ResponseEntity<String> {
        val group: String = userApi.addUser(userDto)

        val location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(group)
                .toUri()

        return ResponseEntity.created(location).body(group)


    }

}
