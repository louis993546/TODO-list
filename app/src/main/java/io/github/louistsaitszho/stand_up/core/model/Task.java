package io.github.louistsaitszho.stand_up.core.model;

import androidx.annotation.NonNull;

import com.google.common.base.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                Objects.equal(title, task.title) &&
                state == task.state &&
                Objects.equal(startDate, task.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, title, state, startDate);
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
