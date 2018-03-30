package pl.lunchbuddy.server.group.api

import groovy.json.JsonSlurper
import org.hamcrest.Matchers
import org.junit.Rule
import org.springframework.http.MediaType
import org.springframework.restdocs.JUnitRestDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import pl.lunchbuddy.server.SpringContextTest
import spock.lang.Shared
import spock.lang.Stepwise

import java.time.LocalTime

import static org.hamcrest.Matchers.notNullValue
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.*
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import static org.springframework.restdocs.payload.PayloadDocumentation.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Stepwise
class GroupEndpointTest extends SpringContextTest {

	@Rule
	JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation('build/generated-snippets')

	private MockMvc mockMvc

	@Shared
	String groupId

	void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(MockMvcRestDocumentation.documentationConfiguration(this.restDocumentation))
				.build()
	}

	def "should return new group link when creating group"() {
		given:
		def cmd = new CreateGroupCmd("jan", fakeUser.id, LocalTime.of(11, 00), LocalTime.of(12, 00))

		when:
		def result = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/group")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(cmd)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("groupId", Matchers.is(notNullValue())))
				.andDo(document("group-create",
				responseFields(
						fieldWithPath("groupId").description("New Group ID")),
				requestBody(
						beneathPath("name"))
		)).andReturn()

		groupId = new JsonSlurper().parseText(result.response.contentAsString).groupId

		then:
		groupId
	}

	def "should get group by it's id"() {
		expect:
		this.mockMvc.perform(
				MockMvcRequestBuilders.get("/group/{id}", groupId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("_links.self.href", Matchers.is(notNullValue())))
				.andDo(document("group-get-one",
				responseFields(groupDescription())))
	}

	def "should get all groups"() {
		expect:
		this.mockMvc.perform(
				MockMvcRequestBuilders.get("/group", groupId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("group-get-all",
				responseFields(
						fieldWithPath("_embedded.groupList").description("List of groups"))
						.andWithPrefix("_embedded.groupList.[]", groupDescription())))
	}

	FieldDescriptor[] groupDescription() {
		return [
				fieldWithPath("_links.self.href").description("<<resources-group-links,links>>"),
				fieldWithPath("name").description("Group name"),
				fieldWithPath("mealTime").description("Configuration of group meal time"),
				fieldWithPath("code").description("Group unique code"),
				fieldWithPath("members").description("Collection of group members"),
				fieldWithPath("members.[].name").description("Member name"),
				fieldWithPath("members.[].id").description("Member id"),
				fieldWithPath("members.[].creationDate").description("Member creation date")
		]
	}
}
