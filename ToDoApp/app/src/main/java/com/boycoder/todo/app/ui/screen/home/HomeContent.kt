package com.boycoder.todo.app.ui.screen.home

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.boycoder.todo.app.R
import com.boycoder.todo.app.data.entity.Task
import com.boycoder.todo.app.ui.theme.*
import com.boycoder.todo.app.util.RequestState
import com.boycoder.todo.app.util.showToast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @Author: zhutao
 * @desc:
 */

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun HomeContent(
    allTasks: RequestState<List<Task>>,
    onSwipeToDelete: (Task) -> Unit,
    gotoTaskDetail: (taskId: Int) -> Unit,
    onUpdateTask: (Task) -> Unit
) {
    if (allTasks is RequestState.Success &&
        allTasks.data.isNotEmpty()
    ) {
        HomeTasksColumn(
            tasks = allTasks.data,
            onSwipeToDelete = onSwipeToDelete,
            gotoTaskDetail = gotoTaskDetail,
            onUpdateTask = onUpdateTask
        )
    } else {
        HomeEmptyContent()
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun HomeTasksColumn(
    tasks: List<Task>,
    onSwipeToDelete: (Task) -> Unit,
    gotoTaskDetail: (taskId: Int) -> Unit,
    onUpdateTask: (Task) -> Unit,
) {
    LazyColumn {
        itemsIndexed(
            items = tasks,
            key = { _, task ->
                task.id
            }
        ) { index, task ->
            val size = remember(tasks) {
                tasks.size
            }
            val dismissState = rememberDismissState()
            val dismissDirection = dismissState.dismissDirection
            val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)
            if (isDismissed && dismissDirection == DismissDirection.EndToStart) {
                val scope = rememberCoroutineScope()
                SideEffect {
                    scope.launch {
                        delay(300)
                        onSwipeToDelete(task)
                    }
                }
            }

            val degrees by animateFloatAsState(
                if (dismissState.targetValue == DismissValue.Default)
                    0f
                else
                    -180f
            )

            val isDeleteEnable by remember(degrees) {
                derivedStateOf { degrees == -180f }
            }

            val context = LocalContext.current

            DisposableEffect(key1 = isDeleteEnable) {
                if (isDeleteEnable) {
                    showToast(context, "松手后删除！")
                }
                onDispose {}
            }

            var itemAppeared by remember { mutableStateOf(false) }
            LaunchedEffect(key1 = true) {
                itemAppeared = true
            }

            AnimatedVisibility(
                visible = itemAppeared && !isDismissed,
                enter = slideInHorizontally(
                    animationSpec = tween(
                        durationMillis = 300
                    ),
                    initialOffsetX = { -(it * (index + 1) / (size + 2)) }
                ),
                exit = shrinkVertically(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                )
            ) {
                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.EndToStart),
                    dismissThresholds = { FractionalThreshold(fraction = 0.2f) },
                    background = { SwipeBackground(degrees = degrees) },
                    dismissContent = {
                        TaskItem(
                            task = task,
                            gotoTaskDetail = gotoTaskDetail,
                            onUpdateTask = onUpdateTask
                        )
                    }
                )
            }
        }
    }
}


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun HomeTasksColumn1(
    tasks: List<Task>,
    onSwipeToDelete: (Task) -> Unit,
    gotoTaskDetail: (taskId: Int) -> Unit,
    onUpdateTask: (Task) -> Unit,
) {
    LazyColumn {
        itemsIndexed(
            items = tasks,
            key = { _, task ->
                task.id
            }
        ) { index, task ->
            val size = remember(tasks) {
                tasks.size
            }
            AnimatedVisibility(
                visible = true,
                enter = slideInHorizontally(
                    animationSpec = tween(
                        durationMillis = 300
                    ),
                    initialOffsetX = { -(it * (index + 1) / (size + 2)) }
                ),
                exit = shrinkVertically(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                )
            ) {
                TaskItem(
                    task = task,
                    gotoTaskDetail = gotoTaskDetail,
                    onUpdateTask = onUpdateTask
                )
            }
        }
    }
}

@Composable
fun SwipeBackground(degrees: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Rose7)
            .padding(horizontal = BIG_PADDING),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            modifier = Modifier.rotate(degrees = degrees),
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_icon),
            tint = Color.White
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun TaskItem(
    task: Task,
    gotoTaskDetail: (taskId: Int) -> Unit,
    onUpdateTask: (Task) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colors.taskItemBackground,
        shape = RectangleShape,
        elevation = TASK_ITEM_ELEVATION,
        onClick = {
            gotoTaskDetail(task.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(all = MEDIUM_PADDING)
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    modifier = Modifier.weight(8f),
                    text = task.title,
                    color = MaterialTheme.colors.taskItemText,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Checkbox(checked = task.isDone, onCheckedChange = {
                        onUpdateTask(task.copy(isDone = !task.isDone))
                    })
                }
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = task.desc,
                color = MaterialTheme.colors.taskItemText,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@ExperimentalMaterialApi
@Composable
@Preview
private fun TaskItemPreview() {
    TaskItem(
        task = Task(
            id = 0,
            title = "标题",
            desc = "描述",
            isDone = false
        ),
        gotoTaskDetail = {},
        onUpdateTask = {}
    )
}







