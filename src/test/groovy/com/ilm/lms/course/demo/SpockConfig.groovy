package com.ilm.lms.course.demo

runner {
  optimizeRunOrder true
}


report {
	enabled true
	logFileDir '.'
	logFileName 'spock-report.json'
	logFileSuffix new Date().format('yyyy-MM-dd_HH_mm_ss')
}