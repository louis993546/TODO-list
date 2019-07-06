package io.github.louistsaitszho.louisdailytasklist

import java.util.*

/**
 * Task = a thing that a user can work on
 */

data class Task(val id: UUID, val content: String, val state: State) {
    enum class State { NOT_DONE, IN_PROGRESS, DONE }
}

/**
 * Page = a collection of tasks with the same [Task.State]
 *
 * TODO improvements
 *   - with different criteria
 *   - with different sort order
 */

data class Page(val representingTaskState: Task.State)