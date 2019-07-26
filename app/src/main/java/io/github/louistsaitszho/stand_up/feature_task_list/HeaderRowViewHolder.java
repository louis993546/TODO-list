package io.github.louistsaitszho.stand_up.feature_task_list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.github.louistsaitszho.stand_up.databinding.ViewHolderHeaderRowBinding;

class HeaderRowViewHolder extends RecyclerView.ViewHolder {

    static HeaderRowViewHolder newInstance(ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderHeaderRowBinding binding = ViewHolderHeaderRowBinding.inflate(
                layoutInflater,
                parent,
                false
        );
        return new HeaderRowViewHolder(binding);
    }

    private final ViewHolderHeaderRowBinding binding;

    private HeaderRowViewHolder(@NonNull ViewHolderHeaderRowBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    void bind(String age) {
        binding.setAge(age);
        binding.executePendingBindings();
    }

}
