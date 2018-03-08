package pl.lunchbuddy.server.group.domain

import pl.lunchbuddy.server.user.domain.User
import spock.lang.Specification

class GroupTest extends Specification {

	private User creator
	private Set<MealOption> defaultOptions

	void setup() {
		creator = new User("creator")
		defaultOptions = [new MealOption("pizza")] as Set
	}

	def "new group should contain its creator as a member"() {
		given:
		def group = newGroup("name")

		expect:
		group.members.size() == 1
		group.members.contains(this.creator)
	}

	def "should add member to group"() {
		given:
		def group = newGroup("name")

		when:
		group.addMember(new User("someUser"))

		then:
		group.members.size() == 2 // member + creator
	}

	def "should not allow to add member to returned member collection"() {
		given:
		def group = newGroup("name")

		expect:
		group.members.size() == 1

		when:
		group.getMembers().add(new User("anotherUser"))

		then:
		group.members.size() == 1
		thrown(UnsupportedOperationException)
	}

	def "each new groups should have its own unique code"() {
		when:
		def group1 = newGroup("name1")
		def group2 = newGroup("name2")
		def group3 = newGroup("name3")

		then:
		group1.code != group2.code != group3.code
	}

	def "group should be created with at leas one meal option"() {
		when:
		def group = new Group("name", creator, defaultOptions)

		then:
		group.mealOptions.size() == defaultOptions.size()

		when:
		def group2 = new Group("name", creator, [] as Set)

		then:
		thrown(IllegalArgumentException)
	}

	def "should add new meal option to group"() {
		given:
		def group = newGroup("name")

		def option = new MealOption("option")
		when:
		group.addMealOption(option)

		then:
		group.mealOptions.contains(option)
	}



	private Group newGroup(String name) {
		new Group(name, creator, defaultOptions)
	}
}
