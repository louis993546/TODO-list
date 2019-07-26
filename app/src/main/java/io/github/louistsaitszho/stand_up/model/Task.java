package io.github.louistsaitszho.stand_up.model;

import androidx.annotation.NonNull;

import org.threeten.bp.LocalDate;

import java.util.UUID;

public class Task {
    @NonNull
    UUID id;

    @NonNull
    String title;

    @NonNull
    TaskState state;

    @NonNull
    LocalDate startDate;

    public Task(@NonNull UUID id,
                @NonNull String title,
                @NonNull TaskState state,
                @NonNull LocalDate startDate) {
        this.id = id;
        this.title = title;
        this.state = state;
        this.startDate = startDate;
    }
}
