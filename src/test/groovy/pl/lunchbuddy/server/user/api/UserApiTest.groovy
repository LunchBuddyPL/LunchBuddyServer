package pl.lunchbuddy.server.user.api

import spock.lang.Specification

class UserApiTest extends Specification {

	def "user should be created with provided name, user id should be returned"() {
		given:
		def api = new UserModuleConfig().userApi()
		def name = "name"

		when:
		def id = api.addUser(new UserDto(name))

		then:
		id
		api.getUser(id).name == name

	}
}
