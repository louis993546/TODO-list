package io.github.louistsaitszho.stand_up.data.local;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.threeten.bp.LocalDate;

import io.github.louistsaitszho.stand_up.model.TaskState;

@Entity(tableName = "task")
public class TaskEntity {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "title")
    @NonNull
    String title;

    @ColumnInfo(name = "state")
    @NonNull
    TaskState state;

    @ColumnInfo(name = "startDate")
    @NonNull
    LocalDate startDate;

    public TaskEntity(@NonNull int id,
                      @NonNull String title,
                      @NonNull TaskState state,
                      @NonNull LocalDate startDate) {
        this.id = id;
        this.title = title;
        this.state = state;
        this.startDate = startDate;
    }

    /**
     * When you are creating a new task, you might not have an id. Accorind to
     * {@link androidx.room.Insert}, "int PK = 0" means the id is not set.
     *
     * @param title
     * @param state
     * @param startDate
     * @Ignore because this constructor is only useful for task creation, Room don't need to know
     * this exists
     */
    @Ignore
    public TaskEntity(@NonNull String title, @NonNull TaskState state, @NonNull LocalDate startDate) {
        this.id = 0;
        this.title = title;
        this.state = state;
        this.startDate = startDate;
    }
}
