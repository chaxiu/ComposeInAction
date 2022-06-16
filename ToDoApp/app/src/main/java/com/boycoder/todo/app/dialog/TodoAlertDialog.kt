package com.boycoder.todo.app.dialog

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.stringResource
import com.boycoder.todo.app.R

/**
 * @Author: zhutao
 * @desc:
 */

@Composable
fun TodoAlertDialog(
    title: String,
    msg: String,
    isShowDialog: Boolean,
    onNoClicked: () -> Unit,
    onYesClicked: () -> Unit,
) {
    if (isShowDialog) {
        AlertDialog(
            title = {
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = msg,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onYesClicked()
                        onNoClicked()
                    })
                {
                    Text(text = stringResource(id = R.string.yes))
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { onNoClicked() })
                {
                    Text(text = stringResource(id = R.string.no))
                }
            },
            onDismissRequest = { onNoClicked() }
        )
    }
}









