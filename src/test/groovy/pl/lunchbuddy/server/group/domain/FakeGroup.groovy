package pl.lunchbuddy.server.group.domain

import pl.lunchbuddy.server.user.domain.User

import java.time.LocalTime

class FakeGroup {

	public final LocalTime FROM = LocalTime.of(10,00)
	public final LocalTime TO = FROM.plusHours(1)
	public final User CREATOR = new User("name")
	public final String GROUP_NAME = "any"
	public final MealTime MEAL_TIME = new MealTime(FROM, TO)
	public final Group INSTANCE

	FakeGroup() {
		this.INSTANCE = new Group(GROUP_NAME, MEAL_TIME, CREATOR, [new MealOption("any")] as Set)
	}

}
