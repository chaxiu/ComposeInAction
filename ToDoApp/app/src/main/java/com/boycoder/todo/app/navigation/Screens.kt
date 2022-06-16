package com.boycoder.todo.app.navigation

import androidx.navigation.NavHostController
import com.boycoder.todo.app.util.AppHolder
import com.boycoder.todo.app.util.AppHolder.HOME_SCREEN
import com.boycoder.todo.app.util.AppHolder.SPLASH_SCREEN

/**
 * @Author: zhutao
 * @desc:
 */

class Screens(navController: NavHostController) {

    val gotoHomeFromSplash: () -> Unit = {
        navController.navigate(route = HOME_SCREEN) {
            popUpTo(SPLASH_SCREEN) { inclusive = true }
        }
    }

    val gotoTaskDetail: (Int) -> Unit = { taskId ->
        navController.navigate(route = "${AppHolder.TASK_DETAIL_BASE}$taskId")
    }

    val gotoHomeScreen: () -> Unit = {
        navController.navigate(route = HOME_SCREEN) {
            popUpTo(HOME_SCREEN) { inclusive = true }
        }
    }
}