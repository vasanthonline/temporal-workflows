package com.temporal.workflows

import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import io.temporal.client.WorkflowStub
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.worker.WorkerFactory
import org.springframework.context.annotation.Bean


class Worker {

    @Bean
    fun initWorker() {
        val service = WorkflowServiceStubs.newLocalServiceStubs()
        val client = WorkflowClient.newInstance(service)

        val factory: WorkerFactory = WorkerFactory.newInstance(client)
        val worker = factory.newWorker(GREETING_TASK_QUEUE)
        worker.registerWorkflowImplementationTypes(GreetingWorkflowImpl::class.java)
        worker.registerActivitiesImplementations(GreetingActivitiesImpl())
        factory.start()

        runWorker()
    }

    private fun runWorker() {
        val service = WorkflowServiceStubs.newLocalServiceStubs()
        val client = WorkflowClient.newInstance(service)

        val options = WorkflowOptions.newBuilder()
            .setWorkflowId(WORKFLOW_ID)
            .setTaskQueue(GREETING_TASK_QUEUE)
            .build()
        val workflow: GreetingWorkflow = client.newWorkflowStub(GreetingWorkflow::class.java, options)
        val greeting = workflow.getGreeting("World")

        val workflowId = WorkflowStub.fromTyped(workflow).execution.workflowId
        println("Workflow ID: $workflowId; Greeting: $greeting.")
    }

    companion object {
        const val WORKFLOW_ID = "GreetingWorkflowID";
        const val GREETING_TASK_QUEUE = "greetingTaskQueue"
    }
}