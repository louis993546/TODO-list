package io.github.louistsaitszho.stand_up.core.data;

import androidx.annotation.NonNull;

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
        return null;
    }

    @NonNull
    @Override
    public Single<Task> createTask(@NonNull String title, @NonNull LocalDate startDate) {
//        return Single.just(new TaskEntity(title, TaskState.TODO, startDate))
//                .flatMap(dao::insertTask)
//                .map(TaskRepositoryImpl::sanitizeInsertResult)
//                .flatMapMaybe(id -> dao.selectTaskByID(Ints.checkedCast(id)))
//                .map(taskEntity -> new Task(taskEntity.id, taskEntity.title, taskEntity.state, taskEntity.startDate));
        return null;
    }

    @NonNull
    @Override
    public Single<Task> updateTaskState(int id, @NonNull TaskState newTaskState) {
        return null;
    }

    @NonNull
    @Override
    public Single<Task> updateTaskTitle(int id, @NonNull String newTitle) {
        return null;
    }
}
