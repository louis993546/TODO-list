package io.github.louistsaitszho.stand_up;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskAdapter extends RecyclerView.Adapter<TaskRowViewHolder> {
    @Override
    public int getItemCount() {
        return 0;
    }

    @NonNull
    @Override
    public TaskRowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TaskRowViewHolder.newInstance(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskRowViewHolder holder, int position) {
        // TODO get item by position
        // holder.bind();
    }
}
