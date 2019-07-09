package io.github.louistsaitszho.louisdailytasklist

import java.util.*

interface TaskRepository {
    fun getTasks(state: Task.State): List<Task>
    fun addTask(content: String, state: Task.State)
    fun editTaskContent(taskID: UUID, newContent: String)
    fun editTaskState(taskID: UUID, newState: Task.State)
}

//class TaskRepositoryImpl(
//    private val database: Database
//) : TaskRepository {
//    override fun getTasks(state: Task.State): List<Task> {
//        TODO("not implemented")
////        return database
////            .taskEntityQueries
////            .getTaskList(state) { i, c, s -> Task(i, c, s) }
////            .executeAsList()
//    }
//
//    override fun addTask(content: String, state: Task.State) {
//        TODO("not implemented")
////        do {
////            val result = runCatching { database.taskEntityQueries.insertOne(UUID.randomUUID(), content, state) }
////        } while (result.isFailure)
//    }
//
//    override fun editTaskContent(taskID: UUID, newContent: String) {
//        TODO("not implemented")
////        database.taskEntityQueries.editContent(newContent, taskID)
//    }
//
//    override fun editTaskState(taskID: UUID, newState: Task.State) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//}

class TempTaskRepositoryImpl : TaskRepository {
    private val database = mutableListOf<Task>()

    override fun getTasks(state: Task.State): List<Task> {
        return database.filter { it.state == state }
    }

    override fun addTask(content: String, state: Task.State) {
        database.add(Task(UUID.randomUUID(), content, state))
    }

    override fun editTaskContent(taskID: UUID, newContent: String) {
        val taskPos = database.indexOfFirst { it.id == taskID }
        val task = database[taskPos]
        val newTask = task.copy(content = newContent)
        database[taskPos] = newTask
    }

    override fun editTaskState(taskID: UUID, newState: Task.State) {
        val taskPos = database.indexOfFirst { it.id == taskID }
        val task = database[taskPos]
        val newTask = task.copy(state = newState)
        database[taskPos] = newTask
    }
}