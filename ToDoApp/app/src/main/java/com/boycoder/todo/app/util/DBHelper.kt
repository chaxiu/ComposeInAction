package com.boycoder.todo.app.util

import androidx.room.Room
import com.boycoder.todo.app.data.ToDoDatabase

/**
 * @Author: zhutao
 * @desc:
 */
object DBHelper {
    private fun provideRoomDB() = Room.databaseBuilder(
        AppHolder.appContext,
        ToDoDatabase::class.java,
        AppHolder.DB_NAME
    ).build()

    fun provideDao(database: ToDoDatabase = provideRoomDB()) = database.toDoDao()
}