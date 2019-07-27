package io.github.louistsaitszho.stand_up.core.data;

import android.database.sqlite.SQLiteCantOpenDatabaseException;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.threeten.bp.LocalDate;

import java.util.Collections;
import java.util.List;

import io.github.louistsaitszho.stand_up.core.data.local.TaskEntity;
import io.github.louistsaitszho.stand_up.core.data.local.TaskEntityDao;
import io.github.louistsaitszho.stand_up.core.model.Task;
import io.github.louistsaitszho.stand_up.core.model.TaskState;
import io.reactivex.Single;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskRepositoryImplTest {

    @Mock
    TaskEntityDao mockDao;

    TaskRepositoryImpl taskRepositoryImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        taskRepositoryImpl = new TaskRepositoryImpl(mockDao);
    }

    @Test
    public void GIVEN_noTasksExists_WHEN_getAllTasks_THEN_successWithNoTasks() {
        //GIVEN
        when(mockDao.selectAllTasks()).thenReturn(Single.just(Collections.emptyList()));

        //WHEN
        Single<List<Task>> result = taskRepositoryImpl.getAllTasks();

        //THEN
        result.test()
                .assertNoErrors()
                .assertResult(Collections.emptyList())
                .dispose();
    }

    @Test
    public void GIVEN_thereAreTasks_WHEN_getAllTasks_THEN_successWithTasks() {
        //GIVEN
        List<TaskEntity> fakeTaskEntities = Lists.newArrayList(
                new TaskEntity(1, "1", TaskState.DONE, LocalDate.parse("2019-07-27"))
        );
        when(mockDao.selectAllTasks()).thenReturn(Single.just(fakeTaskEntities));

        //WHEN
        Single<List<Task>> result = taskRepositoryImpl.getAllTasks();

        //THEN
        List<Task> expectation = Lists.newArrayList(
                new Task(1, "1", TaskState.DONE, LocalDate.parse("2019-07-27"))
        );
        result.test()
                .assertNoErrors()
                .assertResult(expectation)
                .dispose();
        ;
    }

    @Test
    public void GIVEN_failedToReachDB_WHEN_getAllTasks_THEN_fail() {
        //GIVEN
        when(mockDao.selectAllTasks()).thenReturn(Single.error(new SQLiteCantOpenDatabaseException()));

        //WHEN
        Single<List<Task>> result = taskRepositoryImpl.getAllTasks();

        //THEN
        result.test()
                .assertError(throwable -> throwable instanceof SQLiteCantOpenDatabaseException)
                .assertNoValues()
                .dispose();
    }

    @Test
    public void GIVEN_taskExists_WHEN_getTaskByID_THEN_retrieveThatTask() {
        //GIVEN
        int id = 123;
        String title = "title";
        TaskState state = TaskState.DONE;
        LocalDate startTime = LocalDate.now();
        TaskEntity fakeRes = new TaskEntity(id, title, state, startTime);
        when(mockDao.selectTaskByID(id)).thenReturn(Single.just(Optional.of(fakeRes)));

        //WHEN
        Single<Optional<Task>> result = taskRepositoryImpl.getTaskByID(id);

        //THEN
        Task expectation = new Task(id, title, state, startTime);
        result.test()
                .assertNoErrors()
                .assertResult(Optional.of(expectation))
                .dispose();
    }

}