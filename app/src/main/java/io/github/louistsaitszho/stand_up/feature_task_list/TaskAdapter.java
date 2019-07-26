package io.github.louistsaitszho.stand_up.feature_task_list;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.louistsaitszho.stand_up.core.model.Task;
import timber.log.Timber;

public class TaskAdapter extends RecyclerView.Adapter<TaskRowViewHolder> {

    private static final String TAG = "TaskAdapter";

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
        Timber.tag(TAG).d("Binding for position %s", position);
        holder.bind(taskList.get(position));
    }

    /**
     * TODO use diffUtil to calculate diff + do it in non-UI thread
     *
     * @param taskList
     */
    void resetTaskList(List<Task> taskList) {
        this.taskList = taskList;
        this.notifyDataSetChanged();
    }
}
