package io.github.louistsaitszho.stand_up.data.local;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.threeten.bp.LocalDate;

import java.util.UUID;

import io.github.louistsaitszho.stand_up.model.TaskState;

@Entity(tableName = "task")
public class TaskEntity {

    @PrimaryKey
    @NonNull
    UUID id;

    @ColumnInfo(name = "title")
    @NonNull
    String title;

    @ColumnInfo(name = "state")
    @NonNull
    TaskState state;

    @ColumnInfo(name = "startDate")
    @NonNull
    LocalDate startDate;

    public TaskEntity(@NonNull UUID id,
                      @NonNull String title,
                      @NonNull TaskState state,
                      @NonNull LocalDate startDate) {
        this.id = id;
        this.title = title;
        this.state = state;
        this.startDate = startDate;
    }
}
