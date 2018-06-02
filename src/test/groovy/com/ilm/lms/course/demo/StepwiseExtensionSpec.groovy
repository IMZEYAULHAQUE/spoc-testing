package com.ilm.lms.course.demo

import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
class StepwiseExtensionSpec extends Specification {
	
	def "step 1"() {
		expect:
		true
	}
	
	def "step 2"() {
		expect:
		false
	}
	
	def "step 3"() {
		expect:
		true
	}

}
