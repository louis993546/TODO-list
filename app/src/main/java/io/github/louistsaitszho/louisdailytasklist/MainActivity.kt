package io.github.louistsaitszho.louisdailytasklist

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.squareup.sqldelight.android.AndroidSqliteDriver
import io.github.louistsaitszho.louisdailytasklist.databinding.ActivityMainBinding
import io.github.louistsaitszho.louisdailytasklist.databinding.ViewHolderTaskEntryBinding
import io.github.louistsaitszho.louisdailytasklist.databinding.ViewHolderTaskNormalBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setupPages(binding.tabLayout, binding.viewPager)
    }

    private fun setupPages(tabLayout: TabLayout, viewPager: ViewPager2) {

    }
}

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ServiceLocator.setApplicationContext(this)
    }
}

object ServiceLocator {
    //global scope
    private lateinit var applicationContext: Context

    fun setApplicationContext(applicationContext: Context) {
        this.applicationContext = applicationContext
    }

    val database: Database by lazy {
        Database(AndroidSqliteDriver(Database.Schema, applicationContext, "louis.db"))
    }
}

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

typealias Index = Int

class TaskAdapter(
    private val itemCounter: () -> Int,
    private val itemAt: (Index) -> Task
) : RecyclerView.Adapter<TaskViewHolder>() {
    override fun getItemCount() = itemCounter()

    override fun getItemViewType(position: Int): Int {
        TODO()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder = when (viewType) {
        TaskEntryViewHolder.VIEW_TYPE -> TaskEntryViewHolder.create(parent)
        TaskNormalViewHolder.VIEW_TYPE -> TaskNormalViewHolder.create(parent)
        else -> error("Unexpected viewType: $viewType")
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        when (holder) {
            is TaskEntryViewHolder -> holder.bind(itemAt(position))     //TODO IOOB Exception
            is TaskNormalViewHolder -> holder.bind(itemAt(position))
        }
    }
}

fun ViewGroup.layoutInflater(): LayoutInflater = LayoutInflater.from(this.context)

enum class TaskState { NOT_DONE, IN_PROGRESS, DONE }

data class Task(
    val id: UUID,
    val content: String,
    val state: TaskState
)

interface Repository {
    fun getAllTasks(): List<Task>
    fun addTask(content: String, state: TaskState)
    fun editTaskContent(taskID: UUID, newContent: String)
    fun editTaskState(taskID: UUID, newState: TaskState)
}

class RepositoryImpl(
    private val database: Database
) : Repository {
    override fun getAllTasks() = database
        .taskQueries
        .getAllTasks { id, content -> Task(UUID.fromString(id), content, TaskState.NOT_DONE) }
        .executeAsList()

    //TODO use [state]
    override fun addTask(content: String, state: TaskState) {
        do {
            val result = runCatching { database.taskQueries.insertOne(UUID.randomUUID().toString(), content) }
        } while (result.isFailure)
    }

    override fun editTaskContent(taskID: UUID, newContent: String) {
        database.taskQueries.editContent(newContent, taskID.toString())
    }

    override fun editTaskState(taskID: UUID, newState: TaskState) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}