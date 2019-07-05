package io.github.louistsaitszho.louisdailytasklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.github.louistsaitszho.louisdailytasklist.databinding.ActivityMainBinding
import io.github.louistsaitszho.louisdailytasklist.databinding.ViewHolderTaskListBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setupPages(binding.tabLayout, binding.viewPager)
    }

    private fun setupPages(tabLayout: TabLayout, viewPager: ViewPager2) {
        viewPager.adapter = ViewPagerAdapter(PageRepositoryImpl())
        TabLayoutMediator(tabLayout, viewPager, true) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.label_todo)
                1 -> getString(R.string.label_doing)
                2 -> getString(R.string.label_done)
                else -> error("WTF")
            }
        }.attach()
//        tabLayout.setupWithViewPager(viewPager)   //TODO Does not exist yet
    }
}

class TaskListViewHolder(private val binding: ViewHolderTaskListBinding) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.recyclerViewTaskList.layoutManager = LinearLayoutManager(itemView.context)
    }

    fun bind() {
        binding.recyclerViewTaskList.adapter = TaskAdapter(
            itemCounter = { 3 },
            itemAt = { Task(UUID.randomUUID(), "TODO", TaskState.NOT_DONE) }
        )
    }

    companion object {
        fun create(parent: ViewGroup) = TaskListViewHolder(
            ViewHolderTaskListBinding.inflate(parent.layoutInflater(), parent, false)
        )
    }
}

class ViewPagerAdapter(private val pageRepository: PageRepository) : RecyclerView.Adapter<TaskListViewHolder>() {
    override fun getItemCount(): Int = pageRepository.getSize()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TaskListViewHolder.create(parent)

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        holder.bind()
    }
}

typealias Index = Int

class TaskAdapter(
    private val itemCounter: () -> Int,
    private val itemAt: (Index) -> Task
) : RecyclerView.Adapter<TaskViewHolder>() {
    override fun getItemCount() = itemCounter()

    override fun getItemViewType(position: Int): Int {
        return TaskEntryViewHolder.VIEW_TYPE   //TODO
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