package io.github.louistsaitszho.stand_up.core.data;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.louistsaitszho.stand_up.core.data.local.AppDatabase;

@Module
public class DataModule {

    @Singleton
    @Provides
    AppDatabase provideAppDatabase(Context applicationContext) {
        return Room.databaseBuilder(
                applicationContext,
                AppDatabase.class,
                "TODO.db"
        ).build();
    }

}
