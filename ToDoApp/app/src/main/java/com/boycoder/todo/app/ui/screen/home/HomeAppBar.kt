package com.boycoder.todo.app.ui.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.boycoder.todo.app.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.boycoder.todo.app.dialog.TodoAlertDialog
import com.boycoder.todo.app.ui.theme.*

/**
 * @Author: zhutao
 * @desc:
 */

@Composable
fun HomeAppBar(
    onDeleteAllConfirmed : () -> Unit
) {
    HomeTopAppBar(
        onDeleteAllConfirmed = {
            onDeleteAllConfirmed()
        }
    )
}

@Composable
fun HomeTopAppBar(
    onDeleteAllConfirmed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.list_screen_title),
                color = MaterialTheme.colors.topAppBarContent
            )
        },
        actions = {
            HomeAppBarActions(
                onDeleteAllConfirmed = onDeleteAllConfirmed
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackground
    )
}

@Composable
fun HomeAppBarActions(
    onDeleteAllConfirmed: () -> Unit
) {
    var isShowDialog by remember { mutableStateOf(false) }

    TodoAlertDialog(
        title = stringResource(id = R.string.delete_all_tasks),
        msg = stringResource(id = R.string.delete_all_tasks_confirmation),
        isShowDialog = isShowDialog,
        onNoClicked = { isShowDialog = false },
        onYesClicked = { onDeleteAllConfirmed() }
    )

    DeleteAllAction(onDeleteAllConfirmed = { isShowDialog = true })
}

@Composable
fun DeleteAllAction(
    onDeleteAllConfirmed: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded = true }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_more),
            contentDescription = stringResource(id = R.string.delete_all_action),
            tint = MaterialTheme.colors.topAppBarContent
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onDeleteAllConfirmed()
                }
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = MEDIUM_PADDING),
                    text = stringResource(id = R.string.delete_all_action),
                    style = Typography.subtitle2
                )
            }
        }
    }
}

@Composable
@Preview
private fun HomeAppBarPreview() {
    HomeTopAppBar(
        onDeleteAllConfirmed = {}
    )
}