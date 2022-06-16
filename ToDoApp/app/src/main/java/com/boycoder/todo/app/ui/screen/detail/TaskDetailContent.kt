package com.boycoder.todo.app.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.boycoder.todo.app.R
import com.boycoder.todo.app.ui.theme.MEDIUM_PADDING
import com.boycoder.todo.app.ui.theme.SMALL_PADDING
import com.boycoder.todo.app.ui.theme.Transparent

/**
 * @Author: zhutao
 * @desc:
 */

@Composable
fun TaskDetailContent(
    modifier: Modifier = Modifier,
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    isDone: Boolean,
    isDoneChange: (Boolean) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(all = MEDIUM_PADDING)
    ) {

        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(8F),
                value = title,
                onValueChange = { onTitleChange(it) },
                label = { Text(stringResource(id = R.string.enter_title)) },
                placeholder = { Text(text = stringResource(id = R.string.title)) },
                textStyle = MaterialTheme.typography.body1,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Transparent
                )
            )

            Checkbox(
                modifier = Modifier.weight(1F),
                checked = isDone,
                onCheckedChange = isDoneChange
            )
        }

        Divider(
            modifier = Modifier.height(SMALL_PADDING),
            color = MaterialTheme.colors.background
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            value = description,
            onValueChange = { onDescriptionChange(it) },
            label = { Text(stringResource(id = R.string.enter_description)) },
            placeholder = { Text(text = stringResource(id = R.string.description)) },
            textStyle = MaterialTheme.typography.body1,
        )
    }
}


@Composable
@Preview
private fun TaskContentPreview() {
    TaskDetailContent(
        title = "",
        onTitleChange = {},
        description = "",
        onDescriptionChange = {},
        isDone = false,
        isDoneChange = {}
    )
}











