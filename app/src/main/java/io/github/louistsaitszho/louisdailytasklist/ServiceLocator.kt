package io.github.louistsaitszho.louisdailytasklist

import android.content.Context

object ServiceLocator {
    //global scope
    private lateinit var applicationContext: Context

    fun setApplicationContext(applicationContext: Context) {
        this.applicationContext = applicationContext
    }

//    private val database: Database by lazy {
//        Database(AndroidSqliteDriver(Database.Schema, applicationContext, "louis.db"))
//    }

    val taskRepository: TaskRepository  by lazy { TempTaskRepositoryImpl() }

    val pageRepository: PageRepository by lazy { PageRepositoryImpl() }
}