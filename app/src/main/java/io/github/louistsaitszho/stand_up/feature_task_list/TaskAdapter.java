package io.github.louistsaitszho.stand_up.feature_task_list;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.louistsaitszho.stand_up.core.model.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskRowViewHolder> {
    private List<Task> taskList;

    @Override
    public int getItemCount() {
        return taskList == null ? 0 : taskList.size();
    }

    @NonNull
    @Override
    public TaskRowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TaskRowViewHolder.newInstance(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskRowViewHolder holder, int position) {
        holder.bind(taskList.get(position));
    }

    public void resetTaskList(List<Task> taskList) {
        this.taskList = taskList;
        this.notifyDataSetChanged();
    }
}
