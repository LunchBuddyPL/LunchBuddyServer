package pl.lunchbuddy.server.group.api

import io.restassured.http.ContentType
import org.hamcrest.Matchers
import org.springframework.http.HttpStatus
import pl.lunchbuddy.server.SpringContextTest

import java.time.LocalTime

import static io.restassured.RestAssured.get
import static io.restassured.RestAssured.given

class GroupEndpointTest extends SpringContextTest {

	def "should return new group location when creating group"() {
		given:

		def cmd = new CreateGroupCmd("jan", fakeUser.id, LocalTime.of(11, 00), LocalTime.of(12, 00))

		when: 'Posting new group data'
		def groupCreationResponse = given().body(mapper.writeValueAsString(cmd))
				.contentType(ContentType.JSON)
				.post("/group")

		then: 'New group created'
		groupCreationResponse.statusCode() == HttpStatus.CREATED.value()

		and: 'Group can be fetched by location header'

		get(groupCreationResponse.header("location")).then()
				.body("name", Matchers.equalTo(cmd.name))
	}
}
