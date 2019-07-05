package io.github.louistsaitszho.louisdailytasklist

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver

object ServiceLocator {
    //global scope
    private lateinit var applicationContext: Context

    fun setApplicationContext(applicationContext: Context) {
        this.applicationContext = applicationContext
    }

    val database: Database by lazy {
        Database(
            AndroidSqliteDriver(
                Database.Schema,
                applicationContext,
                "louis.db"
            )
        )
    }
}