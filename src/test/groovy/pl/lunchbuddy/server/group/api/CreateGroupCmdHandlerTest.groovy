package pl.lunchbuddy.server.group.api

import pl.lunchbuddy.server.group.InMemoryGroupRepository
import pl.lunchbuddy.server.user.InMemoryUserRepository
import pl.lunchbuddy.server.user.api.UserApi
import pl.lunchbuddy.server.user.api.UserModuleConfig
import pl.lunchbuddy.server.user.api.UserNotFoundException
import pl.lunchbuddy.server.user.api.UserRepository
import pl.lunchbuddy.server.user.domain.FakeUser
import spock.lang.Specification

import java.time.LocalTime

class CreateGroupCmdHandlerTest extends Specification {

	static UserApi userApi
	static CreateGroupCmdHandler command
	static GroupRepository repository
	static UserRepository userRepository

	def from = LocalTime.now()
	def to = from.plusHours(1)

	def static fakeUser = new FakeUser()

	void setupSpec() {
		repository = new InMemoryGroupRepository()
		userRepository = new InMemoryUserRepository()
		userApi = new UserModuleConfig().userApi(userRepository)
		command = new GroupModuleConfig().createGroupCommandHandler(repository, userApi, { event -> event })

		userRepository.save(fakeUser.INSTANCE)

	}

	def "should throw exception when no creator was found"() {
		given:
		def NOT_EXISTING_CREATOR = "NOT_EXISTING_CREATOR"

		when:
		command.execute(new CreateGroupCmd("name", NOT_EXISTING_CREATOR, from, to))

		then:
		thrown(UserNotFoundException)
	}

	def "should create group with provided name and creator"() {
		given:
		def groupName = "name"


		when:
		def id = command.execute(new CreateGroupCmd(groupName, fakeUser.INSTANCE.id, from, to))

		then:
		id
		with(repository.findById(id)) {
			name == groupName
			members.size() == 1
			members.find({ m -> m.id == fakeUser.INSTANCE.id })
			mealTime.start == from
			mealTime.end == to
		}

	}

}
