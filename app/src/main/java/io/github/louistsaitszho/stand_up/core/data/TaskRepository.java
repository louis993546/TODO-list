package io.github.louistsaitszho.stand_up.core.data;

import androidx.annotation.NonNull;

import org.threeten.bp.LocalDate;

import java.util.List;

import io.github.louistsaitszho.stand_up.core.model.Task;
import io.github.louistsaitszho.stand_up.core.model.TaskState;
import io.reactivex.Maybe;
import io.reactivex.Single;

interface TaskRepository {
    @NonNull
    Single<List<Task>> getAllTasks();

    @NonNull
    Maybe<Task> getTaskByID(int id);

    @NonNull
    Single<Task> createTask(@NonNull String title, @NonNull LocalDate startDate);

    @NonNull
    Single<Task> updateTaskState(int id, @NonNull TaskState newTaskState);

    @NonNull
    Single<Task> updateTaskTitle(int id, @NonNull String newTitle);
}