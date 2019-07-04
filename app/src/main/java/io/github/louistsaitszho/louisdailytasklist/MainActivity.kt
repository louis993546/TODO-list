package io.github.louistsaitszho.louisdailytasklist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}

class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    companion object {
        fun create() {

        }
    }
}

class TaskEntryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    companion object {
        fun create() {

        }
    }
}

enum class TaskState { NOT_DONE, IN_PROGRESS, DONE }

data class Task(
    val id: UUID,
    val content: String,
    val state: TaskState
)