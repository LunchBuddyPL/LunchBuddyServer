package pl.lunchbuddy.server.user.api

import pl.lunchbuddy.server.user.InMemoryUserRepository
import pl.lunchbuddy.server.user.domain.FakeUser
import spock.lang.Specification

class UserApiTest extends Specification {

	static UserApi api
	static UserRepository repository

	void setupSpec() {
		repository = new InMemoryUserRepository()
		api = new UserModuleConfig().userApi(repository)
	}

	def "should find user by given id"() {
		given:
		def fakeUser = new FakeUser()
		repository.save(fakeUser.INSTANCE)

		when:
		def result = api.getUser(fakeUser.INSTANCE.id)

		then:
		result
		result.id == fakeUser.INSTANCE.id

	}

}
