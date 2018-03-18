package pl.lunchbuddy.server.user.api

import pl.lunchbuddy.server.user.InMemoryUserRepository
import spock.lang.Specification

class CreateUserCmdHandlerTest extends Specification {

	static CreateUserCmdHandler command
	static UserRepository repository

	void setupSpec() {
		repository = new InMemoryUserRepository()
		command = new UserModuleConfig().createUserCommandHandler(repository, { event -> event })

	}

	def "should create user with provided name"() {
		given:
		def userName = "name"

		when:
		def id = command.execute(new CreateUserCmd(userName))

		then:
		id
		with(repository.findById(id)) {
			name == userName
		}

	}

}
