package com.xie.learn.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.generated.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.utils.rememberDestinationsNavigator
import com.ramcosta.composedestinations.utils.toDestinationsNavigator
import com.xie.learn.ui.main.home.HomeScreen
import com.xie.learn.ui.main.view.MainItemView
import com.xie.learn.ui.theme.LearnTheme

/**
 * @author  xie
 * @date    2024/07/17
 * @des     MainActivity
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearnTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navigator = navController.rememberDestinationsNavigator()
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            MainItemView(title = "home") {
                navigator.navigate(HomeScreenDestination())
            }

            MainItemView(title = "home") {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LearnTheme {
        MainScreen()
    }
}
