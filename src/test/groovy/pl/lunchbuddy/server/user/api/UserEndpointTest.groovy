package pl.lunchbuddy.server.user.api

import groovy.json.JsonSlurper
import org.hamcrest.Matchers
import org.junit.Rule
import org.springframework.http.MediaType
import org.springframework.restdocs.JUnitRestDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import pl.lunchbuddy.server.SpringContextTest
import spock.lang.Shared
import spock.lang.Stepwise

import static org.hamcrest.Matchers.notNullValue
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import static org.springframework.restdocs.payload.PayloadDocumentation.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Stepwise
class UserEndpointTest extends SpringContextTest {

	@Rule
	JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation('build/generated-snippets')

	private MockMvc mockMvc

	@Shared
	String userId

	void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(MockMvcRestDocumentation.documentationConfiguration(this.restDocumentation))
				.build()
	}

	def "should return new user link when creating user"() {
		given:
		def cmd = new CreateUserCmd("John Smith")

		when:
		def result = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(cmd)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("userId", Matchers.is(notNullValue())))
				.andDo(document("user-create",
				responseFields(
						fieldWithPath("userId").description("New user ID")),
				requestBody(
						beneathPath("name"))
		)).andReturn()

		userId = new JsonSlurper().parseText(result.response.contentAsString).userId

		then:
		userId
	}

	def "should get user by it's id"() {
		expect:
		this.mockMvc.perform(
				MockMvcRequestBuilders.get("/user/{id}", userId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("user-get-one",
				responseFields(
						fieldWithPath("id").description("User ID"),
						fieldWithPath("name").description("User name"),
						fieldWithPath("creationDate").description("User creation date"),
						fieldWithPath("_links.self.href").description("Link to user")
				)))
	}
}