package com.ilm.lms.course.demo

import spock.lang.Ignore
import spock.lang.IgnoreRest
import spock.lang.PendingFeature
import spock.lang.Specification

class PublisherSubscriberSpec extends Specification {
	
	def publisher = new Publisher()
	
	def subscriberOne   = Mock(Subscriber)
	def subscriberTwo   = Mock(Subscriber)
	def subscriberThree = Mock(Subscriber)
	def subscriberFour  = Mock(Subscriber)
	
	def setup() {
		publisher.subscribe(subscriberOne)
	}
	
	def "subscriber count should be as added"() {
		
		when:
		publisher.subscribe(subscriberTwo)
		publisher.subscribers << subscriberThree
		
		then:
		publisher.subscribers.size() == 3
		
	}
	
	def "subscriber count should not be incremental"() {
		
		expect:
		publisher.subscribe(subscriberTwo)
		publisher.subscribers.size() == 2
		
		and:
		!publisher.subscribe(subscriberTwo)
		publisher.subscribers.size() == 2
		
	}
	
	def "deliver events to all subscribers"() {
		publisher.subscribers << subscriberTwo  << subscriberFour
		
		def event = "Getting Ready"
		
		when:
		publisher.publish(event)
		
		then:
		1 * subscriberOne.receive(event)
		1 * subscriberTwo.receive(event)
		
		then:
		0 * subscriberThree.receive(event)
		1 * subscriberFour.receive(event)
		
	}
	
	@Ignore
	def "publish events should return 'event published'"() {
		publisher.subscribers << subscriberTwo  << subscriberThree << subscriberFour
		
		def event = "Some Event"
		
		expect:
		publisher.publish(event) == 'event published'
		
	}
	
	def "subscriber receiving null event should throw some exception"() {
		
		subscriberOne.receive(_) >> {throw new RuntimeException()}
		publisher.subscribers << subscriberTwo
		
		when: 
		publisher.publish("test")
		
		then:
		1 * subscriberOne.receive("test")
		//RuntimeException e = thrown()
		//1 * subscriberOne.receive(_) >> {throw new RuntimeException()}
		//thrown RuntimeException == subscriberOne.receive("test") // Need to find out how it will be working where only one subscriber is throwing exception. 
		
		then:
		1 * subscriberTwo.receive("test")
		
		/*
		Possible check. Depend on requirement. 
		
		1 * subscriber.receive("hello")      // exactly one call
		0 * subscriber.receive("hello")      // zero calls
		(1..3) * subscriber.receive("hello") // between one and three calls (inclusive)
		(1.._) * subscriber.receive("hello") // at least one call
		(_..3) * subscriber.receive("hello") // at most three calls
		_ * subscriber.receive("hello")      // any number of calls, including zero
		1 * _.receive("hello")          	 // a call to any mock object
		1 * subscriber./r.*e/("hello")  	 // a method whose name matches the given regular expression
		
		1 * subscriber.receive("hello")     		// an argument that is equal to the String "hello"
		1 * subscriber.receive(!"hello")    		// an argument that is unequal to the String "hello"
		1 * subscriber.receive()            		// the empty argument list (would never match in our example)
		1 * subscriber.receive(_)           		// any single argument (including null)
		1 * subscriber.receive(*_)          		// any argument list (including the empty argument list)
		1 * subscriber.receive(!null)       		// any non-null argument
		1 * subscriber.receive(_ as String) 		// any non-null argument that is-a String
		1 * subscriber.receive({ it.size() > 3 }) 	// an argument that satisfies the given predicate
		
		1 * subscriber._(*_)     // any method on subscriber, with any argument list
		1 * subscriber._         // shortcut for and preferred over the above
		
		1 * _._                  // any method call on any mock object
		1 * _                    // shortcut for and preferred over the above
		(..) * .(*_) >> _ 		 // is a valid interaction declaration, it is neither good style nor particularly useful
		
		_ * subscriber._                  // allow any interaction with 'subscriber'
		0 * _                           // don't allow any other interaction

		*/
		
		/*
		when:
		publisher.send("hello")
		
		then:
		interaction {
		  def message = "hello"
		  1 * subscriber.receive(message)
		}
		*/
		
	}
	
	def "publishing null event should throw some exception"() {
		publisher.subscribers << subscriberTwo
		
		when:
		publisher.publish(null)
		
		then:
		thrown RuntimeException
	}
	
	@IgnoreRest
	def "publishing value should receive"() {
		
		given:
		subscriberTwo.receive(!"exception") >>> ["ok", "ok", "failed"] >> ["good", "well done"] >> "wow"   // chaining method resposes
		subscriberTwo.receive("exception") >> {throw new RuntimeException()}
		publisher.subscribers << subscriberTwo
		
		
		when:
		publisher.publish("ok")
		publisher.publish("good")
		
		then:
		2 * subscriberOne.receive(_)
		2 * subscriberTwo.receive(_)
		
		when:
		publisher.publish("exception")
		
		then:
		1 * subscriberOne.receive(_)
		thrown RuntimeException
	}
	
	@PendingFeature
	def "test exception thrown in case of null"(){
		expect:
		false
	}
	
}

class Publisher {
	
	def subscribers = [] as LinkedHashSet;
	
	def subscribe(Subscriber s) {
		subscribers.add(s)
	}
	
	def publish(event) {
		
		if (event == null) {
			throw RuntimeException
		}
		
		/*subscribers.each({
			it -> 
			try {
				it.receive(event)
			} catch (RuntimeException  ex) {}
			
		})*/
		
		//shortcut way of above code in groovy
		subscribers*.receive(event)
		
		return "event published"
	}
}

interface Subscriber {
	def String receive(event)
}