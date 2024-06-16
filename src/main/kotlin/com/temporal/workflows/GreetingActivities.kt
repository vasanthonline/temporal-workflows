package com.temporal.workflows

import io.temporal.activity.ActivityInterface;

@ActivityInterface
interface GreetingActivities {

    fun composeGreeting(name: String):  String

}