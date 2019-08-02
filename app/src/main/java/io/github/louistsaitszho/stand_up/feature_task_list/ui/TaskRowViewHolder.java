package io.github.louistsaitszho.stand_up.feature_task_list.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.github.louistsaitszho.stand_up.core.model.Task;
import io.github.louistsaitszho.stand_up.databinding.ViewHolderTaskRowBinding;

class TaskRowViewHolder extends RecyclerView.ViewHolder {

    static TaskRowViewHolder newInstance(ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderTaskRowBinding binding = ViewHolderTaskRowBinding.inflate(
                layoutInflater,
                parent,
                false
        );
        return new TaskRowViewHolder(binding);
    }

    private final ViewHolderTaskRowBinding binding;

    private TaskRowViewHolder(@NonNull ViewHolderTaskRowBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    void bind(Task task) {
        binding.setTask(task);
        binding.executePendingBindings();
    }
}
