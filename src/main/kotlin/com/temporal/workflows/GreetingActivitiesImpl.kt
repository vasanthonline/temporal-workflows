package com.temporal.workflows

class GreetingActivitiesImpl: GreetingActivities {
    override fun composeGreeting(name: String): String {
        return "Hello $name!"
    }

}