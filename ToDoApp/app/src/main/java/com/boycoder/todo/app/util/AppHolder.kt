package com.boycoder.todo.app.util

import android.app.Application

/**
 * @Author: zhutao
 * @desc:
 */
object AppHolder {
    const val SPLASH_DELAY = 3000L

    const val SPLASH_SCREEN = "splash"
    const val HOME_SCREEN = "home"
    const val TASK_DETAIL_BASE = "task_detail/"
    const val TASK_ARG_KEY = "taskId"
    const val TASK_DETAIL_SCREEN = "${TASK_DETAIL_BASE}{${TASK_ARG_KEY}}"

    const val DB_TABLE = "todo_table"
    const val DB_NAME = "todo_db"

    lateinit var appContext: Application
}