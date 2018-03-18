package pl.lunchbuddy.server.group.domain

import spock.lang.Specification

import java.time.LocalTime

class MealTimeTest extends Specification {

	def "should throw exception when end time is before start time"() {
		when:
		new MealTime(start, end)

		then:
		thrown(IllegalStateException)

		where:
		start                | end
		LocalTime.of(11, 00) | LocalTime.of(10, 00)
	}
}
