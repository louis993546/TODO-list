package io.github.louistsaitszho.stand_up.data.local;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import com.google.common.base.Strings;

import org.threeten.bp.LocalDate;

import java.util.UUID;

public class GeneralRoomConverters {
    @TypeConverter
    @Nullable
    public static LocalDate localDateFromString(@Nullable String input) {
        return Strings.isNullOrEmpty(input) ? null : LocalDate.parse(input);
    }

    @TypeConverter
    @Nullable
    public static String stringFromLocalDate(@Nullable LocalDate input) {
        return input == null ? null : input.toString();
    }

    @TypeConverter
    @Nullable
    public static UUID uuidFromString(@Nullable String input) {
        return Strings.isNullOrEmpty(input) ? null : UUID.fromString(input);
    }

    @TypeConverter
    @Nullable
    public static String stringFromUUID(@Nullable UUID input) {
        return input == null ? null : input.toString();
    }
}
