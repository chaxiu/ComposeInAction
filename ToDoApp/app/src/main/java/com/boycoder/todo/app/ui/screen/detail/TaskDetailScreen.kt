package com.boycoder.todo.app.ui.screen.detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.boycoder.todo.app.data.entity.Task
import com.boycoder.todo.app.ui.viewmodels.MainViewModel
import com.boycoder.todo.app.util.showToast

/**
 * @Author: zhutao
 * @desc:
 */

@Composable
fun TaskDetailScreen(
    currentTask: Task?,
    mainViewModel: MainViewModel,
    gotoHomeScreen: () -> Unit
) {
    val title: String by mainViewModel.currentTaskTitle
    val description: String by mainViewModel.currentTaskDesc
    val isDone: Boolean by mainViewModel.isCurrentTaskDone

    val context = LocalContext.current

    BackHandler {
        gotoHomeScreen()
    }

    Scaffold(
        topBar = {
            TaskDetailAppBar(
                currentTask = currentTask,
                gotoHomeScreen = {
                    gotoHomeScreen()
                },
                onNewTaskClicked = {
                    if (mainViewModel.isCurrentTaskValid()) {
                        mainViewModel.createTask()
                        gotoHomeScreen()
                        showToast(context, "任务${mainViewModel.currentTaskTitle.value}创建成功！")
                    } else {
                        showToast(context, "任务为空，请填写内容。")
                    }
                },
                onUpdateTaskClicked = {
                    if (mainViewModel.isCurrentTaskValid()) {
                        mainViewModel.updateTask()
                        gotoHomeScreen()
                        showToast(context, "任务${mainViewModel.currentTaskTitle.value}更新成功！")
                    } else {
                        showToast(context, "任务为空，请填写内容。")
                    }
                },
                onDeleteTaskClicked = {
                    if (mainViewModel.isCurrentTaskValid()) {
                        mainViewModel.deleteTask()
                        gotoHomeScreen()
                        showToast(context, "任务${mainViewModel.currentTaskTitle.value}删除成功！")
                    } else {
                        showToast(context, "任务为空，请填写内容。")
                    }

                }
            )
        },
        content = {
            TaskDetailContent(
                modifier = Modifier.padding(it),
                title = title,
                onTitleChange = {
                    mainViewModel.updateTitle(it)
                },
                description = description,
                onDescriptionChange = {
                    mainViewModel.currentTaskDesc.value = it
                },
                isDone = isDone,
                isDoneChange = {
                    mainViewModel.isCurrentTaskDone.value = it
                },
            )
        }
    )
}