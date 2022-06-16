package com.boycoder.todo.app.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.boycoder.todo.app.ui.screen.home.HomeScreen
import com.boycoder.todo.app.ui.screen.splash.Splash
import com.boycoder.todo.app.ui.screen.detail.TaskDetailScreen
import com.boycoder.todo.app.ui.viewmodels.MainViewModel
import com.boycoder.todo.app.util.AppHolder
import com.boycoder.todo.app.util.AppHolder.SPLASH_SCREEN

/**
 * @Author: zhutao
 * @desc:
 */

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun SetupNavigation(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {

        composable(
            route = SPLASH_SCREEN,
        ) {
            Splash(gotoHomeScreen = screen.gotoHomeFromSplash)
        }

        composable(
            route = AppHolder.HOME_SCREEN
        ) {
            HomeScreen(
                gotoTaskDetail = screen.gotoTaskDetail,
                mainViewModel = mainViewModel
            )
        }

        composable(
            route = AppHolder.TASK_DETAIL_SCREEN,
            arguments = listOf(navArgument(AppHolder.TASK_ARG_KEY) {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val taskId = navBackStackEntry.arguments?.getInt(AppHolder.TASK_ARG_KEY) ?: -1
            LaunchedEffect(key1 = taskId) {
                mainViewModel.searchTask(taskId = taskId)
            }

            val currentTask by mainViewModel.currentTask.collectAsState()
            LaunchedEffect(key1 = currentTask) {
                if (currentTask != null || taskId == -1) {
                    mainViewModel.updateCurrentTaskFields(currentTask = currentTask)
                }
            }

            TaskDetailScreen(
                currentTask = currentTask,
                mainViewModel = mainViewModel,
                gotoHomeScreen = screen.gotoHomeScreen
            )
        }
    }
}