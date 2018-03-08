package pl.lunchbuddy.server.group.api

import pl.lunchbuddy.server.user.api.UserApi
import pl.lunchbuddy.server.user.api.UserDto
import pl.lunchbuddy.server.user.api.UserModuleConfig
import spock.lang.Specification

class GroupApiTest extends Specification {

	static UserApi userApi
	static GroupApi api
	static String creatorId

	void setupSpec() {
		userApi = new UserModuleConfig().userApi()
		api = new GroupModuleConfig().groupApi(userApi)
		creatorId = userApi.addUser(new UserDto("any"))
	}

	def "should throw exception when no creator was found"() {
		given:
		def NOT_EXISTING_CREATOR = "NOT_EXISTING_CREATOR"

		when:
		api.addGroup(new GroupDto("name", NOT_EXISTING_CREATOR))

		then:
		thrown(IllegalStateException)
	}

	def "should create group with provided name and creator"() {
		given:
		def groupName = "name"

		when:
		def id = api.addGroup(new GroupDto(groupName, creatorId))

		then:
		id
		with(api.getGroup(id)) {
			name == groupName
			members.size() == 1
			members.find({ m -> m.id == creatorId })
		}

	}

	def "should find group by given id"() {
		given:
		def id = api.addGroup(new GroupDto("name", creatorId))

		when:
		def result = api.getGroup(id)

		then:
		result
		result.code == id

	}

	def "should find all groups"() {
		given:
		def cleanApi = new GroupModuleConfig().groupApi(userApi)

		5.times { cleanApi.addGroup(new GroupDto("name", creatorId)) }

		when:
		def result = cleanApi.getAllGroups()

		then:
		result
		result.size() == 5
	}
}
