package com.example.model_todo.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.model_todo.local.dao.TodoDao
import com.example.model_todo.response.Todo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): TodoDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext, TodoDatabase::class.java, "todo.db"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(TodoDatabaseCallback(scope))
                    .build()
                    .also { todoDatabase -> INSTANCE = todoDatabase }
            }
        }

        private class TodoDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.todoDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(todoDao: TodoDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            todoDao.deleteAll()

            listOf(
                Todo(title = "Title 1", content = "Lorem Ipsum Dipsum TipSum"),
                Todo(title = "Title 2", content = "Lorem Ipsum Dipsum TipSum"),
                Todo(title = "Title 3", content = "Lorem Ipsum Dipsum TipSum", isComplete = true),
                Todo(title = "Title 4", content = "Lorem Ipsum Dipsum TipSum"),
                Todo(title = "Title 5", content = "Lorem Ipsum Dipsum TipSum"),
            ).forEach { todo -> todoDao.insert(todo) }

        }
    }
}