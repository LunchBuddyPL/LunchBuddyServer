package pl.lunchbuddy.server.util

import spock.lang.Specification

class ShortCodeGeneratorTest extends Specification {

	CodeGenerator codeGenerator
	private int NUMBER_OF_CHARS = 8

	void setup() {
		codeGenerator = new ShortCodeGenerator(NUMBER_OF_CHARS)
	}

	def "should return 8 chars long unique codes, only uppercase  "() {
		given:
		def set = [] as Set<String>

		when:
		100.times { set.add(codeGenerator.generate()) }

		then:
		set.size() == 100
		set.findAll { it -> it.matches("[A-Z0-9]{8}")}

	}
}
