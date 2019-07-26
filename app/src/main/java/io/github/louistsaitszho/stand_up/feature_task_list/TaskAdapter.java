package io.github.louistsaitszho.stand_up.feature_task_list;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.common.base.Preconditions;

import java.util.List;

import io.github.louistsaitszho.stand_up.core.model.Task;
import timber.log.Timber;

public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "TaskAdapter";

    private List<Task> taskList;

    @Override
    public int getItemCount() {
        return taskList == null ? 1 : taskList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 0 : 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return viewType == 0 ? newHeaderRow(parent) : newTaskRow(parent);
    }

    private HeaderRowViewHolder newHeaderRow(ViewGroup parent) {
        return HeaderRowViewHolder.newInstance(parent);
    }

    private TaskRowViewHolder newTaskRow(ViewGroup parent) {
        return TaskRowViewHolder.newInstance(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Timber.tag(TAG).d("Binding for position %s", position);

        if (holder.getItemViewType() == 0) {
            bindHeaderRow(holder);
        } else {
            bindTaskRow(holder, taskList.get(position - 1));
        }
    }

    private void bindHeaderRow(RecyclerView.ViewHolder holder) {
        Preconditions.checkArgument(holder instanceof HeaderRowViewHolder);
        HeaderRowViewHolder vh = (HeaderRowViewHolder) holder;
        vh.bind("123");
    }

    private void bindTaskRow(RecyclerView.ViewHolder holder, Task task) {
        Preconditions.checkArgument(holder instanceof TaskRowViewHolder);
        TaskRowViewHolder vh = (TaskRowViewHolder) holder;
        vh.bind(task);
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
