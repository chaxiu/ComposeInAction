package com.boycoder.todo.app.ui.screen.detail

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.boycoder.todo.app.R
import com.boycoder.todo.app.dialog.TodoAlertDialog
import com.boycoder.todo.app.data.entity.Task
import com.boycoder.todo.app.ui.theme.topAppBarBackground
import com.boycoder.todo.app.ui.theme.topAppBarContent

/**
 * @Author: zhutao
 * @desc:
 */

@Composable
fun TaskDetailAppBar(
    currentTask: Task?,
    gotoHomeScreen: () -> Unit,
    onNewTaskClicked: () -> Unit,
    onUpdateTaskClicked: () -> Unit,
    onDeleteTaskClicked: () -> Unit
) {
    if (currentTask == null) {
        NewTaskAppBar(gotoHomeScreen = gotoHomeScreen,
            onNewTaskClicked = onNewTaskClicked
        )
    } else {
        UpdateTaskAppBar(
            currentTask = currentTask,
            gotoHomeScreen = gotoHomeScreen,
            onDeleteTaskClicked = onDeleteTaskClicked,
            onUpdateTaskClicked = onUpdateTaskClicked
        )
    }
}

@Composable
fun NewTaskAppBar(
    gotoHomeScreen: () -> Unit,
    onNewTaskClicked: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            BackButton(onBackClicked = gotoHomeScreen)
        },
        title = {
            Text(
                text = stringResource(id = R.string.add_task),
                color = MaterialTheme.colors.topAppBarContent
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackground,
        actions = {
            NewTaskTopBarAction(onNewTaskClicked = onNewTaskClicked)
        }
    )
}

@Composable
fun BackButton(
    onBackClicked: () -> Unit
) {
    IconButton(onClick = { onBackClicked() }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.back_arrow),
            tint = MaterialTheme.colors.topAppBarContent
        )
    }
}

@Composable
fun NewTaskTopBarAction(
    onNewTaskClicked: () -> Unit
) {
    IconButton(onClick = { onNewTaskClicked() }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.add_task),
            tint = MaterialTheme.colors.topAppBarContent
        )
    }
}

@Composable
fun UpdateTaskAppBar(
    currentTask: Task,
    gotoHomeScreen: () -> Unit,
    onUpdateTaskClicked: () -> Unit,
    onDeleteTaskClicked: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            CloseButton(onCloseClicked = gotoHomeScreen)
        },
        title = {
            Text(
                text = currentTask.title,
                color = MaterialTheme.colors.topAppBarContent,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackground,
        actions = {
            ExistingTaskAppBarActions(
                currentTask = currentTask,
                onDeleteTaskClicked = onDeleteTaskClicked,
                onUpdateTaskClicked = onUpdateTaskClicked
            )
        }
    )
}

@Composable
fun CloseButton(
    onCloseClicked: () -> Unit
) {
    IconButton(onClick = { onCloseClicked() }) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(id = R.string.close_icon),
            tint = MaterialTheme.colors.topAppBarContent
        )
    }
}

@Composable
fun ExistingTaskAppBarActions(
    currentTask: Task,
    onUpdateTaskClicked: () -> Unit,
    onDeleteTaskClicked: () -> Unit
) {
    var isShowDialog by remember { mutableStateOf(false) }

    TodoAlertDialog(
        title = stringResource(
            id = R.string.delete_task,
            currentTask.title
        ),
        msg = stringResource(
            id = R.string.delete_task_confirmation,
            currentTask.title
        ),
        isShowDialog = isShowDialog,
        onNoClicked = { isShowDialog = false },
        onYesClicked = { onDeleteTaskClicked() }
    )

    DeleteButton(onDeleteTaskClicked = { isShowDialog = true })
    UpdateButton(onUpdateTaskClicked = onUpdateTaskClicked)
}

@Composable
fun DeleteButton(
    onDeleteTaskClicked: () -> Unit
) {
    IconButton(onClick = { onDeleteTaskClicked() }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_icon),
            tint = MaterialTheme.colors.topAppBarContent
        )
    }
}

@Composable
fun UpdateButton(
    onUpdateTaskClicked: () -> Unit
) {
    IconButton(onClick = { onUpdateTaskClicked() }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.update_icon),
            tint = MaterialTheme.colors.topAppBarContent
        )
    }
}


@Composable
@Preview
private fun NewTaskAppBarPreview() {
    NewTaskAppBar(
        gotoHomeScreen = {},
        onNewTaskClicked = {}
    )
}

@Composable
@Preview
private fun ExistingTaskAppBarPreview() {
    UpdateTaskAppBar(
        currentTask = Task(
            id = 0,
            title = "测试标题",
            desc = "测试详情",
            isDone = false
        ),
        gotoHomeScreen = {},
        onDeleteTaskClicked = {},
        onUpdateTaskClicked = {}
    )
}













