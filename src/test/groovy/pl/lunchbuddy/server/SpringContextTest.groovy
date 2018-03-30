package pl.lunchbuddy.server

import com.fasterxml.jackson.databind.ObjectMapper
import io.restassured.RestAssured
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.context.WebApplicationContext
import pl.lunchbuddy.server.user.api.UserRepository
import pl.lunchbuddy.server.user.domain.FakeUser
import pl.lunchbuddy.server.user.domain.User
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest(classes = LunchbuddyServerApplication, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
class SpringContextTest extends Specification {

	@Shared
	boolean initialized

	@Autowired
	ObjectMapper mapper

	@Autowired
	WebApplicationContext context

	static User fakeUser

	@Autowired
	void poorMansSetupSpec(Environment environment, UserRepository repository) {
		if (!initialized) {
			RestAssured.port = environment.getProperty("local.server.port", Integer)
			fakeUser = new FakeUser().INSTANCE
			repository.save(fakeUser)
			initialized = true
		}
	}

}
