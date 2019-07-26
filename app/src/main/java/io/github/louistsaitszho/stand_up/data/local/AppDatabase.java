package io.github.louistsaitszho.stand_up.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {TaskEntity.class}, version = 1)
@TypeConverters({GeneralRoomConverters.class, ModelRoomConverters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskEntityDao taskEntityDao();
}
