package com.boycoder.todo.app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.boycoder.todo.app.data.entity.Task

/**
 * @Author: zhutao
 * @desc:
 */

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class ToDoDatabase: RoomDatabase() {

    abstract fun toDoDao(): ToDoDao

}