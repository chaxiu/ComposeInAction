package com.boycoder.todo.app.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.boycoder.todo.app.R
import com.boycoder.todo.app.ui.theme.fabBackground
import com.boycoder.todo.app.ui.viewmodels.MainViewModel
import com.boycoder.todo.app.util.showToast

/**
 * @Author: zhutao
 * @desc:
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    gotoTaskDetail: (taskId: Int) -> Unit,
    mainViewModel: MainViewModel
) {

    val allTasks by mainViewModel.allTasks.collectAsState()

    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeAppBar(
                onDeleteAllConfirmed = {
                    mainViewModel.deleteAllTasks()
                    showToast(context, "所有任务都已清空!")
                }
            )
        },
        content = {
            HomeContent(
                allTasks = allTasks,
                onSwipeToDelete = { task ->
                    mainViewModel.updateCurrentTaskFields(currentTask = task)
                    mainViewModel.deleteTask()
                    showToast(context, "${task.title}任务已删除！")
                },
                gotoTaskDetail = gotoTaskDetail,
                onUpdateTask = { task ->
                    mainViewModel.updateCurrentTaskFields(currentTask = task)
                    mainViewModel.updateTask()
                    showToast(context, "${task.title}任务已更新！")
                }
            )
        },
        floatingActionButton = {
            HomeFab(onFabClicked = gotoTaskDetail)
        }
    )
}

@Composable
fun HomeFab(
    onFabClicked: (taskId: Int) -> Unit
) {
    FloatingActionButton(
        onClick = {
            onFabClicked(-1)
        },
        backgroundColor = MaterialTheme.colors.fabBackground
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(
                id = R.string.add_button
            ),
            tint = Color.White
        )
    }
}
