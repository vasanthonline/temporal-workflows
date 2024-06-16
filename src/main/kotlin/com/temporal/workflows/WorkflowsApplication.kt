package com.temporal.workflows

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WorkflowsApplication

fun main(args: Array<String>) {
	runApplication<WorkflowsApplication>(*args)
}
