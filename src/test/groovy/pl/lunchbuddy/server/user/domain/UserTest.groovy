package pl.lunchbuddy.server.user.domain

import spock.lang.Specification

class UserTest extends Specification {

	def "should generate id when creation user"() {
		expect:
		new User(_ as String).id
	}

	def "generated id should be unique"() {
		expect:
		new User("1").id != new User("1").id != new User("2").id
	}
}
