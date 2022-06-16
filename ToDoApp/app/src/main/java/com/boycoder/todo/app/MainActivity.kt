package com.boycoder.todo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.boycoder.todo.app.navigation.SetupNavigation
import com.boycoder.todo.app.ui.theme.ToDoComposeTheme
import com.boycoder.todo.app.ui.viewmodels.MainViewModel

/**
 * @Author: zhutao
 * @desc:
 */

@ExperimentalAnimationApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoComposeTheme {
                navController = rememberNavController()
                SetupNavigation(
                    navController = navController,
                    mainViewModel = mainViewModel
                )
            }
        }
    }
}
