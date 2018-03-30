package pl.lunchbuddy.server.user.api

import pl.lunchbuddy.server.user.InMemoryUserRepository
import spock.lang.Specification

class CreateUserCmdHandlerTest extends Specification {

	static CreateUserCmdHandler command
	static UserRepository repository

	void setupSpec() {
		repository = new InMemoryUserRepository()
		command = new UserModuleConfig().createUserCommandHandler(repository)

	}

	def "should create user with provided name"() {
		given:
		def userName = "name"

		when:
		def event = command.execute(new CreateUserCmd(userName))

		then:
		event
		with(repository.findById(event.userId)) {
			name == userName
		}

	}

}
