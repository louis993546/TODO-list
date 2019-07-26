package io.github.louistsaitszho.stand_up.core.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.common.base.Optional;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface TaskEntityDao {

    @Insert
    Single<List<Long>> insertTask(TaskEntity... taskEntities);

    @Update
    void updateTasks(TaskEntity taskEntity);

    @Query("SELECT * FROM task ORDER BY startDate")
    Single<List<TaskEntity>> selectAllTasks();

    @Query("SELECT * FROM task WHERE id = :id")
    Single<Optional<TaskEntity>> selectTaskByID(int id);
}
