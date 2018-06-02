package com.ilm.lms.course.demo

import groovy.sql.Sql
import spock.lang.Shared
import spock.lang.Specification

class DatabaseDrivenSpec extends Specification {
	
	@Shared sql = Sql.newInstance("jdbc:h2:mem:demo", "org.h2.Driver")
	
	def setupSpec() {
		sql.execute("create table maxdata (id int primary key, a int, b int, c int)")
		sql.execute("insert into maxdata values (1, 5, 9, 9)")
		sql.execute("insert into maxdata values (2, 15, 90, 90)")
		
		sql.execute("create table maxdatav2 (id int primary key, a int, b int, na int, c int)")
		sql.execute("insert into maxdatav2 values (1, 5, 9, 45, 9)")
		sql.execute("insert into maxdatav2 values (2, 15, 90, 78, 90)")
	}
	
	def "max of #a and #b is #c version1"() {
		expect:
		Math.max(a, b) == c
		
		where:
		[a,b,c] << sql.rows("select a, b, c from maxdata")
		
	}
	
	def "max of #a and #b is #c version2"() {
		expect:
		Math.max(a, b) == c
		
		where:
		[_,a,b,_,c] << sql.rows("select * from maxdatav2")
		
	}
	
	def "max of #a and #b is #c version3"() {
		expect:
		Math.max(a, b) == c
		
		where:
		row << sql.rows("select * from maxdatav2")
		
		a = row.a
		b = row.b
		c = row.c
		
	}

}
