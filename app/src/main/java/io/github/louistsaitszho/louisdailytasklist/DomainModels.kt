package io.github.louistsaitszho.louisdailytasklist

import java.util.*

/**
 * Task = a thing that a user can work on
 */

enum class TaskState { NOT_DONE, IN_PROGRESS, DONE }

data class Task(val id: UUID, val content: String, val state: TaskState)

/**
 * Page = a collection of tasks with the same [TaskState]
 *
 * TODO improvements
 *   - with different criteria
 *   - with different sort order
 */

data class Page(val representingTaskState: TaskState)