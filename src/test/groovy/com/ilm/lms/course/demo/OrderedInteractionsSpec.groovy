package com.ilm.lms.course.demo

import java.util.concurrent.TimeUnit

import spock.lang.Issue
import spock.lang.Narrative
import spock.lang.PendingFeature
import spock.lang.See
import spock.lang.Specification
import spock.lang.Timeout
import spock.lang.Title


@Title("Title will go here")
@Narrative("""
	Invocation and interaction 
	can be ordered by using separate then block.
	See first feature method below.
""")
@See("http://spockframework.org/spock/docs/1.1/index.html")
class OrderedInteractionsSpec extends Specification {

	def serviceThree
	
	def setup() {
		serviceThree = Mock(SomeService)
	}
	
	def "service must be invoked in order"() {
		def serviceOne = Mock(SomeService)
		def serviceTwo = Mock(SomeService)
		
		
		when:
		serviceOne.doSomething()
		serviceThree.doSomething()
		serviceOne.doSomethingBro()
		serviceOne.doSomethingBro()
		
		then:
		1 * serviceOne.doSomething()
		
		then: 
		0 * serviceTwo.doSomething()
		
		then:
		1 * serviceThree.doSomething()
		2 * serviceOne.doSomethingBro()
	}
	
	@PendingFeature
	@Issue("http://my.issues.org/FOO-1")
	def "test exception thrown in case of null"(){
		expect:
		false
	}
	
	@Timeout(5)
	def "I fail if I run for more than five seconds"() { 
		
		given:
		Thread.wait(6000)
		
		expect:
		 true
	}
	
	@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
	def "I better be quick"() { 
		
		expect:
		true
		
	}
	
	
}


interface SomeService {
	def doSomething()
	def doSomethingBro()
}
