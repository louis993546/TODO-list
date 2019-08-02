package io.github.louistsaitszho.stand_up.core.data.local;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import com.google.common.base.Strings;

import io.github.louistsaitszho.stand_up.core.model.TaskState;

/**
 * Suppress WeakerAccess because Room needs {@link TypeConverter} to be public.
 */
@SuppressWarnings("WeakerAccess")
class ModelRoomConverters {
    @TypeConverter
    @Nullable
    public static TaskState taskStateFromString(@Nullable String input) {
        return Strings.isNullOrEmpty(input) ? null : TaskState.valueOf(input);
    }

    @TypeConverter
    @Nullable
    public static String stringFromTaskState(@Nullable TaskState input) {
        return input == null ? null : input.name();
    }
}
