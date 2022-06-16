package com.boycoder.todo.app.util

import android.content.Context
import android.widget.Toast

/**
 * @Author: zhutao
 * @desc:
 */

fun showToast(context: Context, msg: String) {
    Toast.makeText(
        context,
        msg,
        Toast.LENGTH_SHORT
    ).show()
}
