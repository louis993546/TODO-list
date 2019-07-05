package io.github.louistsaitszho.louisdailytasklist

import java.util.*

interface TaskRepository {
    fun getAllTasks(): List<Task>
    fun addTask(content: String, state: TaskState)
    fun editTaskContent(taskID: UUID, newContent: String)
    fun editTaskState(taskID: UUID, newState: TaskState)
}

class TaskRepositoryImpl(
    private val database: Database
) : TaskRepository {
    override fun getAllTasks() = database
        .taskQueries
        .getAllTasks { id, content -> Task(UUID.fromString(id), content, TaskState.NOT_DONE) }
        .executeAsList()

    //TODO use [state]
    override fun addTask(content: String, state: TaskState) {
        do {
            val result = runCatching { database.taskQueries.insertOne(UUID.randomUUID().toString(), content) }
        } while (result.isFailure)
    }

    override fun editTaskContent(taskID: UUID, newContent: String) {
        database.taskQueries.editContent(newContent, taskID.toString())
    }

    override fun editTaskState(taskID: UUID, newState: TaskState) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}