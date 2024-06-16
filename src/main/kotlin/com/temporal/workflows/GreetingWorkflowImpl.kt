package com.temporal.workflows


import io.temporal.activity.ActivityOptions
import io.temporal.workflow.Workflow
import java.time.Duration


class GreetingWorkflowImpl: GreetingWorkflow {

    companion object {
        private val options: ActivityOptions = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(60))
            .build()
        private val activity:GreetingActivities = Workflow.newActivityStub(GreetingActivities::class.java, options)
    }

    override fun getGreeting(name: String): String {
        return activity.composeGreeting(name)
    }

}