package io.github.louistsaitszho.stand_up.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TaskEntityDao {

    @Insert
    long[] insertTask(TaskEntity... taskEntities);

    @Update
    void updateTasks(TaskEntity taskEntity);

    @Query("SELECT * FROM task ORDER BY startDate")
    TaskEntity[] selectAllTasks();
}
