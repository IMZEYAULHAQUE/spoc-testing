package com.ilm.lms.course.demo

import com.ilm.lms.course.utils.StringUtils

import spock.lang.Specification
import spock.lang.Unroll

class TestSpecification extends Specification {

	def "length of string test"() {
		expect:
		string.size() == length

		where:
		string << ["zeyaul", "haque"]
		length << [6, 5]

		/*
		 name 		| length
		 "zeyaul"	| 6
		 "haque"		| 5
		 */
	}

	def "convert to upper case"() {
		expect:
		StringUtils.toUpperCase(string) == result

		where:
		string 			|		result
		"zeyaul"		|		"ZEYAUL"
		"haque"			|		"HAQUE"
		"Mohammad"		|		"MOHAMMAD"
	}

	def "#person.name is a #sex.toLowerCase() person"() {
		expect:
		person.getSex() == sex.toLowerCase()

		where:
		person                    || sex
		new Person(name: "Fred")  || "Male"
		new Person(name: "Wilma") || "Female"
	}

	static class Person {
		String name
		String getSex() {
			name == "Fred" ? "male" : "female"
		}
	}

	def "minimum of #a and #b is #c"() {
		expect:
		Math.min(a,b) == c

		where:
		a | b || c
		3 | 7 || 3
		5 | 4 || 4
		9 | 9 || 9
	}
	
	@Unroll
	def "minimum of #a and #b is #c version2"() {
		expect:
		Math.min(a,b) == c

		where:
		a | _
		3 | _
		7 | _
		0 | _
		
		b << [Math.random() * 100, Math.random() * 100, Math.random() * 100]
		
		c = a > b ? a : b
	}
}
