package pl.lunchbuddy.server.group.api

import pl.lunchbuddy.server.group.InMemoryGroupRepository
import pl.lunchbuddy.server.group.domain.FakeGroup
import pl.lunchbuddy.server.user.InMemoryUserRepository
import pl.lunchbuddy.server.user.api.UserApi
import pl.lunchbuddy.server.user.api.UserModuleConfig
import pl.lunchbuddy.server.user.api.UserNotFoundException
import pl.lunchbuddy.server.user.api.UserRepository
import pl.lunchbuddy.server.user.domain.FakeUser
import spock.lang.Specification

class AddGroupMemberCmdHandlerTest extends Specification {

	static UserApi userApi
	static AddGroupMemberCmdHandler command
	static GroupRepository repository
	static UserRepository userRepository

	def static fakeUser = new FakeUser()
	def static fakeGroup = new FakeGroup()

	void setupSpec() {
		repository = new InMemoryGroupRepository()
		userRepository = new InMemoryUserRepository()
		userApi = new UserModuleConfig().userApi(userRepository)
		command = new GroupModuleConfig().addGroupMemberCommandHandler(repository, userApi, { event -> event })

		userRepository.save(fakeUser.INSTANCE)
		repository.save(fakeGroup.INSTANCE)

	}

	def "should throw exception when no user with given id was found"() {
		given:
		def NOT_EXISTING_USER = "NOT_EXISTING_USER"

		when:
		command.execute(new AddGroupMemberCmd("groupId", NOT_EXISTING_USER))

		then:
		thrown(UserNotFoundException)
	}

	def "should throw exception when no group with given id was found"() {
		given:
		def NOT_EXISTING_GROUP = "NOT_EXISTING_GROUP"

		when:
		command.execute(new AddGroupMemberCmd(NOT_EXISTING_GROUP, fakeUser.INSTANCE.id))

		then:
		thrown(GroupNotFoundException)
	}

	def "should add new user to group and emmit event with group and user id"() {
		when:
		command.execute(new AddGroupMemberCmd(fakeGroup.INSTANCE.code, fakeUser.INSTANCE.id))

		then:
		noExceptionThrown()
		fakeGroup.INSTANCE.members.find({ it -> it.id == fakeUser.INSTANCE.id })

	}
}
