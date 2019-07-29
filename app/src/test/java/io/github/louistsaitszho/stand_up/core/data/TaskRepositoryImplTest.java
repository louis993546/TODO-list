package io.github.louistsaitszho.stand_up.core.data;

import android.database.SQLException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;

import androidx.room.EmptyResultSetException;

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

import io.github.louistsaitszho.stand_up.OddlySpecificException;
import io.github.louistsaitszho.stand_up.core.data.local.TaskEntity;
import io.github.louistsaitszho.stand_up.core.data.local.TaskEntityDao;
import io.github.louistsaitszho.stand_up.core.model.Task;
import io.github.louistsaitszho.stand_up.core.model.TaskState;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskRepositoryImplTest {

    @Mock
    TaskEntityDao mockDao;

    private TaskRepository taskRepositoryImpl;

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
        when(mockDao.selectTaskByID(id)).thenReturn(Maybe.just(fakeRes));

        //WHEN
        Maybe<Task> result = taskRepositoryImpl.getTaskByID(id);

        //THEN
        Task expectation = new Task(id, title, state, startTime);
        result.test()
                .assertNoErrors()
                .assertResult(expectation)
                .dispose();
    }

    @Test
    public void GIVEN_taskDoesNotExist_WHEN_getTaskByID_THEN_getAbsent() {
        //GIVEN
        int id = -1;
        Throwable error = new EmptyResultSetException("entry not found");
        when(mockDao.selectTaskByID(id)).thenReturn(Maybe.error(error));

        //WHEN
        Maybe<Task> result = taskRepositoryImpl.getTaskByID(id);

        //THEN
        result.test()
                .assertNoValues()
                .assertError(error)
                .dispose();
    }

    @Test
    public void GIVEN_somethingElseWentWrong_WHEN_getTaskByID_THEN_fail() {
        //GIVEN
        when(mockDao.selectTaskByID(anyInt())).thenReturn(Maybe.error(new OddlySpecificException()));

        //WHEN
        Maybe<Task> result = taskRepositoryImpl.getTaskByID(1);

        //THEN
        result.test()
                .assertNoValues()
                .assertError(OddlySpecificException.class)
                .dispose();
    }

    @Test
    public void WHEN_createTask_THEN_getsNewTaskWithID() {
        //GIVEN
        String title = "title";
        LocalDate date = LocalDate.parse("2019-07-27");
        when(mockDao.insertTask(new TaskEntity(title, TaskState.TODO, date)))
                .thenReturn(Single.just(Lists.newArrayList(1L)));

        //WHEN
        Single<Task> result = taskRepositoryImpl.createTask(title, date);

        //THEN
        Task expectation = new Task(1, title, TaskState.TODO, date);
        result.test()
                .assertNoErrors()
                .assertValue(expectation)
                .dispose();
    }

    @Test
    public void GIVEN_somethingWentWrong_WHEN_createTask_THEN_fail() {
        //GIVEN
        // TODO give it a value
        String title = "title";
        LocalDate startDate = LocalDate.parse("2019-07-29");
        TaskEntity input = new TaskEntity(title, TaskState.TODO, startDate);
        when(mockDao.insertTask(input)).thenReturn(Single.error(new OddlySpecificException()));

        //WHEN
        Single<Task> result = taskRepositoryImpl.createTask(title, startDate);

        //THEN
        result.test()
                .assertNoValues()
                .assertError(OddlySpecificException.class)
                .dispose();
    }

    @Test
    public void GIVEN_nullLocalDate_WHEN_createTask_THEN_fail() {
        //GIVEN

        //WHEN
        Single<Task> result = taskRepositoryImpl.createTask("title", null);

        //THEN
        result.test()
                .assertError(NullPointerException.class)
                .assertNoValues()
                .dispose();
    }

    @Test
    public void GIVEN_nullTitle_WHEN_createTask_THEN_fail() {
        //GIVEN

        //WHEN
        //noinspection ConstantConditions because this is exactly what it's testing
        Single<Task> result = taskRepositoryImpl.createTask(null, LocalDate.now());

        //THEN
        result.test()
                .assertError(NullPointerException.class)
                .assertNoValues()
                .dispose();
    }

    @Test
    public void GIVEN_thatTaskExists_WHEN_updateTaskState_THEN_getNewTask() {
        //GIVEN
        int id = 1;
        String title = "title";
        TaskState before = TaskState.DOING;
        TaskState after = TaskState.DONE;
        LocalDate startDate = LocalDate.parse("2019-07-29");
        TaskEntity input = new TaskEntity(id, title, before, startDate);
        TaskEntity output = new TaskEntity(id, title, after, startDate);
        when(mockDao.updateTasks(input)).thenReturn(Completable.complete());
        when(mockDao.selectTaskByID(id)).thenReturn(Maybe.just(output));

        //WHEN
        Single<Task> result = taskRepositoryImpl.updateTaskState(id, after);

        //THEN
        Task expectation = new Task(id, title, after, startDate);
        result.test()
                .assertNoErrors()
                .assertValue(expectation)
                .dispose();
    }

    @Test
    public void GIVEN_thatTaskDoesNotExist_WHEN_updateTaskState_THEN_fail() {
        //GIVEN
        int id = -1;
        String title = "title";
        TaskState state = TaskState.DONE;
        LocalDate startDate = LocalDate.now();
        TaskEntity input = new TaskEntity(id, title, state, startDate);
        when(mockDao.updateTasks(input)).thenReturn(Completable.error(new SQLException()));

        //WHEN
        Single<Task> result = taskRepositoryImpl.updateTaskState(id, state);

        //THEN
        result.test()
                .assertNoValues()
                .assertError(SQLException.class)
                .dispose();
    }

    @Test
    public void GIVEN_invalidNewTaskState_WHEN_updateTaskState_THEN_fail() {
        //GIVEN

        //WHEN
        //noinspection ConstantConditions because that's what this is testing
        Single<Task> result = taskRepositoryImpl.updateTaskState(1, null);

        //THEN
        result.test()
                .assertNoValues()
                .assertError(NullPointerException.class)
                .dispose();
    }

    @Test
    public void GIVEN_somethingElseWentWrong_WHEN_updateTaskState_THEN_fail() {
        //GIVEN
        int id = 1;
        String title = "title";
        TaskState taskState = TaskState.DONE;
        LocalDate startDate = LocalDate.now();
        TaskEntity test = new TaskEntity(id, title, taskState, startDate);
        when(mockDao.updateTasks(test)).thenReturn(Completable.error(new OddlySpecificException()));

        //WHEN
        Single<Task> result = taskRepositoryImpl.updateTaskState(id, taskState);

        //THEN
        result.test()
                .assertNoValues()
                .assertError(OddlySpecificException.class)
                .dispose();
    }

    @Test
    public void GIVEN_thatTaskExists_WHEN_updateTaskTitle_THEN_getNewTask() {
        //GIVEN
        int id = 1;
        String newTitle = "new title";
        TaskState state = TaskState.TODO;
        LocalDate startDate = LocalDate.parse("2019-7-29");
        TaskEntity taskEntity = new TaskEntity(id, newTitle, state, startDate);
        when(mockDao.updateTasks(taskEntity)).thenReturn(Completable.complete());
        when(mockDao.selectTaskByID(id)).thenReturn(Maybe.just(taskEntity));

        //WHEN
        Single<Task> result = taskRepositoryImpl.updateTaskTitle(id, newTitle);

        //THEN
        Task expectation = new Task(id, newTitle, state, startDate);
        result.test()
                .assertNoErrors()
                .assertValue(expectation)
                .dispose();
    }

    @Test
    public void GIVEN_thatTaskDoesNotExist_WHEN_updateTaskTitle_THEN_fail() {
        //GIVEN
        int id = -1;
        TaskEntity input = new TaskEntity(id, "title", TaskState.DONE, LocalDate.now());
        when(mockDao.updateTasks(input)).thenReturn(Completable.error(new SQLException()));

        //WHEN
        Single<Task> result = taskRepositoryImpl.updateTaskTitle(id, "anything");

        //THEN
        result.test()
                .assertNoValues()
                .assertError(SQLException.class)
                .dispose();
    }

    @Test
    public void GIVEN_invalidNewTitle_WHEN_updateTaskTitle_THEN_fail() {
        //GIVEN

        //WHEN
        //noinspection ConstantConditions because it is what this test is testing
        Single<Task> result = taskRepositoryImpl.updateTaskTitle(1, null);

        //THEN
        result.test()
                .assertNoValues()
                .assertError(NullPointerException.class)
                .dispose();
    }

    @Test
    public void GIVEN_somethingElseWentWrong_WHEN_updateTaskTitle_THEN_fail() {
        //GIVEN
        int id = 1;
        TaskEntity test = new TaskEntity(id, "title", TaskState.DONE, LocalDate.now());
        when(mockDao.selectTaskByID(id)).thenReturn(Maybe.just(test));
        when(mockDao.updateTasks(test)).thenReturn(Completable.error(new OddlySpecificException()));

        //WHEN
        Single<Task> result = taskRepositoryImpl.updateTaskTitle(id, "anything");

        //THEN
        result.test()
                .assertNoValues()
                .assertError(OddlySpecificException.class)
                .dispose();
    }

}