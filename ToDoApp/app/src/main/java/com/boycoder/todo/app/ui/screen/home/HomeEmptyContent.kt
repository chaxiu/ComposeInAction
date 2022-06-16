package com.boycoder.todo.app.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.boycoder.todo.app.R
import com.boycoder.todo.app.ui.theme.EMPTY_CONTENT_HEIGHT
import com.boycoder.todo.app.ui.theme.Neutral5

/**
 * @Author: zhutao
 * @desc:
 */

@Composable
fun HomeEmptyContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(EMPTY_CONTENT_HEIGHT),
            painter = painterResource(id = R.drawable.ic_baseline_auto_awesome_24),
            contentDescription = stringResource(id = R.string.sad_face_icon),
            tint = Neutral5
        )
        Text(
            text = stringResource(id = R.string.empty_content),
            color = Neutral5,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h6.fontSize
        )
    }
}

@Composable
@Preview
private fun HomeEmptyContentPreview(){
    HomeEmptyContent()
}