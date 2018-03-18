package pl.lunchbuddy.server.group.api

import pl.lunchbuddy.server.group.InMemoryGroupRepository
import pl.lunchbuddy.server.group.domain.FakeGroup
import spock.lang.Specification

class GroupApiTest extends Specification {

	static GroupApi api
	static GroupRepository repository

	void setupSpec() {
		repository = new InMemoryGroupRepository()
		api = new GroupModuleConfig().groupApi(repository)
	}

	def "should find group by given id"() {
		given:
		def fakeGroup = new FakeGroup()
		repository.save(fakeGroup.INSTANCE)

		when:
		def result = api.getGroup(fakeGroup.INSTANCE.code)

		then:
		result
		result.code == fakeGroup.INSTANCE.code

	}
}
