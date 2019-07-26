package io.github.louistsaitszho.stand_up;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.github.louistsaitszho.stand_up.databinding.ViewHolderTaskRowBinding;
import io.github.louistsaitszho.stand_up.model.Task;

public class TaskRowViewHolder extends RecyclerView.ViewHolder {

    public static TaskRowViewHolder newInstance(ViewGroup parent) {
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

    public void bind(Task task) {

    }
}
