package io.github.louistsaitszho.louisdailytasklist

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.louistsaitszho.louisdailytasklist.databinding.ViewHolderTaskEntryBinding
import io.github.louistsaitszho.louisdailytasklist.databinding.ViewHolderTaskNormalBinding

sealed class TaskViewHolder(viewType: Int, view: View) : RecyclerView.ViewHolder(view)

class TaskNormalViewHolder(
    private val binding: ViewHolderTaskNormalBinding
) : TaskViewHolder(VIEW_TYPE, binding.root) {

    fun bind(task: Task) {
        binding.content = task.content
    }

    companion object {
        const val VIEW_TYPE = 100

        fun create(parent: ViewGroup): TaskNormalViewHolder = TaskNormalViewHolder(
            ViewHolderTaskNormalBinding.inflate(parent.layoutInflater(), parent, false)
        )
    }
}

class TaskEntryViewHolder(
    private val binding: ViewHolderTaskEntryBinding
) : TaskViewHolder(VIEW_TYPE, binding.root) {

    fun bind(task: Task?) {

    }

    companion object {
        const val VIEW_TYPE = 200
        fun create(parent: ViewGroup): TaskEntryViewHolder = TaskEntryViewHolder(
            ViewHolderTaskEntryBinding.inflate(parent.layoutInflater(), parent, false)
        )
    }
}