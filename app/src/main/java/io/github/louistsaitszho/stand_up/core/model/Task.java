package io.github.louistsaitszho.stand_up.core.model;

import androidx.annotation.NonNull;

import org.threeten.bp.LocalDate;

public class Task {
    public int id;

    @NonNull
    public String title;

    @NonNull
    public TaskState state;

    @NonNull
    public LocalDate startDate;

    public Task(@NonNull int id,
                @NonNull String title,
                @NonNull TaskState state,
                @NonNull LocalDate startDate) {
        this.id = id;
        this.title = title;
        this.state = state;
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", state=" + state +
                ", startDate=" + startDate +
                '}';
    }
}
