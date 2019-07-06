package io.github.louistsaitszho.louisdailytasklist

import java.util.*

interface TaskRepository {
    fun getTasks(state: Task.State): List<Task>
    fun addTask(content: String, state: Task.State)
    fun editTaskContent(taskID: UUID, newContent: String)
    fun editTaskState(taskID: UUID, newState: Task.State)
}

class TaskRepositoryImpl(
    private val database: Database
) : TaskRepository {
    override fun getTasks(state: Task.State): List<Task> = database
        .taskEntityQueries
        .getTaskList(state) { i, c, s -> Task(i, c, s) }
        .executeAsList()

    override fun addTask(content: String, state: Task.State) {
        do {
            val result = runCatching { database.taskEntityQueries.insertOne(UUID.randomUUID(), content, state) }
        } while (result.isFailure)
    }

    override fun editTaskContent(taskID: UUID, newContent: String) {
        database.taskEntityQueries.editContent(newContent, taskID)
    }

    override fun editTaskState(taskID: UUID, newState: Task.State) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}