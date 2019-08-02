package io.github.louistsaitszho.stand_up.core.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import org.threeten.bp.LocalDate;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.github.louistsaitszho.stand_up.core.data.local.TaskEntity;
import io.github.louistsaitszho.stand_up.core.data.local.TaskEntityDao;
import io.github.louistsaitszho.stand_up.core.model.Task;
import io.github.louistsaitszho.stand_up.core.model.TaskState;
import io.reactivex.Maybe;
import io.reactivex.Single;

class TaskRepositoryImpl implements TaskRepository {

    private final TaskEntityDao dao;

    @Inject
    public TaskRepositoryImpl(TaskEntityDao dao) {
        this.dao = dao;
    }

    private static Long sanitizeInsertResult(List<Long> ids) throws IllegalStateException {
        if (ids.size() == 1) return ids.get(0);
        else throw new IllegalStateException(String.format(
                "Somehow you get %d ids back from inserting 1 item !?",
                ids.size()
        ));
    }

    @NonNull
    @Override
    public Single<List<Task>> getAllTasks() {
        return dao.selectAllTasks().map(taskEntities -> Lists.transform(
                taskEntities,
                input -> {
                    TaskEntity t = Objects.requireNonNull(input);
                    return new Task(t.id, t.title, t.state, t.startDate);
                }));
    }

    @NonNull
    @Override
    public Maybe<Task> getTaskByID(int id) {
        return dao.selectTaskByID(id).map(taskEntity -> new Task(
                taskEntity.id,
                taskEntity.title,
                taskEntity.state,
                taskEntity.startDate
        ));
    }

    @NonNull
    @Override
    public Single<Task> createTask(@NonNull String title, @NonNull LocalDate startDate) {
        return Single.fromCallable(() -> new TaskEntity(
                Preconditions.checkNotNull(title),
                TaskState.TODO,
                Preconditions.checkNotNull(startDate)
        ))
                .flatMap(dao::insertTask)
                .map(idList -> idList.get(0).intValue())
                .flatMap(id -> dao.selectTaskByID(id).toSingle())
                .map(taskEntity -> new Task(
                        taskEntity.id,
                        taskEntity.title,
                        taskEntity.state,
                        taskEntity.startDate
                ));
    }

    @NonNull
    @Override
    public Single<Task> updateTaskState(int id, @NonNull TaskState newTaskState) {
        if (newTaskState == null)
            return Single.error(new NullPointerException("newTaskState should not be null"));
        return updateTask(id, null, newTaskState);
    }

    @NonNull
    @Override
    public Single<Task> updateTaskTitle(int id, @NonNull String newTitle) {
        if (newTitle == null)
            return Single.error(new NullPointerException("newTitle should not be null"));
        return updateTask(id, newTitle, null);
    }

    private Single<Task> updateTask(int id, @Nullable String newTitle, @Nullable TaskState newTaskState) {
        return dao.selectTaskByID(id)
                .toSingle()
                .map(taskEntity -> {
                    String title = newTitle == null ? taskEntity.title : newTitle;
                    TaskState state = newTaskState == null ? taskEntity.state : newTaskState;
                    return new TaskEntity(taskEntity.id, title, state, taskEntity.startDate);
                })
                .flatMapCompletable(dao::updateTasks)
                .andThen(dao.selectTaskByID(id))
                .toSingle()
                .map(taskEntity -> new Task(
                        taskEntity.id,
                        taskEntity.title,
                        taskEntity.state,
                        taskEntity.startDate
                ));
    }
}
