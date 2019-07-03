package io.github.louistsaitszho.todolist

import android.app.Application
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}

class App : Application() {
    val database: SqlDriver by lazy {
        AndroidSqliteDriver(schema = Database.Schema, context = this, name = "test.db")
    }
}

class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view)

class TodoAdapter : RecyclerView.Adapter<TodoViewHolder>() {
    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class MainViewModel(
    private val repository: TodoRepository
) : ViewModel()

data class Todo(
    val id: UUID,
    val title: String,
    val state: TodoState
)

enum class TodoState { NOT_DONE, DONE }

interface TodoRepository {
    /*suspend*/ fun createTodos(vararg todos: Todo)
    /*suspend*/ fun readTodos(): List<Todo>
    /*suspend*/ fun updateTodo(id: UUID, newTodo: Todo)
    /*suspend*/ fun deleteTodos(vararg todoIDs: UUID)
}

class TodoRepositoryImpl(
    private val database: Database
) : TodoRepository {

    override /*suspend*/ fun createTodos(vararg todos: Todo) {
        todos.forEach { todo ->
            database.todosQueries.insertOne(todo.id.toString(), todo.title, todo.state.name)
        }
    }

    override /*suspend*/ fun readTodos(): List<Todo> =
        database.todosQueries
            .selectAll { id, title, state -> Todo(UUID.fromString(id), title, TodoState.valueOf(state)) }
            .executeAsList()

    override /*suspend*/ fun updateTodo(id: UUID, newTodo: Todo) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override /*suspend*/ fun deleteTodos(vararg todoIDs: UUID) {
        todoIDs.forEach { database.todosQueries.deleteOne(it.toString()) }
    }

}