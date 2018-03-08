package pl.lunchbuddy.server

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration
@SpringBootTest
class LunchbuddyServerApplicationTest extends Specification {

	@Autowired
	ApplicationContext applicationContext

	def "should load context"() {
		expect:
		applicationContext
	}
}
