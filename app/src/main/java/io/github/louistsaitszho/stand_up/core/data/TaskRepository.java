package io.github.louistsaitszho.stand_up.core.data;

import androidx.annotation.NonNull;

import com.google.common.base.Optional;

import org.threeten.bp.LocalDate;

import java.util.List;

import io.github.louistsaitszho.stand_up.core.model.Task;
import io.github.louistsaitszho.stand_up.core.model.TaskState;
import io.reactivex.Single;

interface TaskRepository {
    @NonNull
    Single<List<Task>> getAllTasks();

    @NonNull
    Single<Optional<Task>> getTaskByID(int id);

    @NonNull
    Single<Task> createTask(@NonNull String title, @NonNull LocalDate startDate);

    @NonNull
    Single<Task> updateTaskState(int id, @NonNull TaskState newTaskState);

    @NonNull
    Single<Task> updateTaskTitle(int id, @NonNull String newTitle);
}