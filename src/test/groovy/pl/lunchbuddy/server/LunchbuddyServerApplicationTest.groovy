package pl.lunchbuddy.server

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext

class LunchbuddyServerApplicationTest extends SpringContextTest {

	@Autowired
	ApplicationContext applicationContext

	def "should load context"() {
		expect:
		applicationContext
	}
}
