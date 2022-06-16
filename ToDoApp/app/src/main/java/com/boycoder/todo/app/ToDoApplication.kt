package com.boycoder.todo.app

import android.app.Application
import com.boycoder.todo.app.util.AppHolder

/**
 * @Author: zhutao
 * @desc:
 */

class ToDoApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppHolder.appContext = this
    }
}