package com.boycoder.todo.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.boycoder.todo.app.util.AppHolder.DB_TABLE

/**
 * @Author: zhutao
 * @desc:
 */

@Entity(tableName = DB_TABLE)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val desc: String,
    val isDone: Boolean
)