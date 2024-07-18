package com.xie.learn.ui.main.home

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.xie.learn.ui.theme.LearnTheme

/**
 * @Author Xie
 * @Date   2024/7/18
 * @Des
 */
@Destination<RootGraph>(start = true)
@Composable
fun HomeScreen(navigator: DestinationsNavigator) {

    HomeView()
}

@Composable
fun HomeView() {
    Box {
        Text(text = "Home Screen")
    }
}

@Preview
@Composable
private fun HomeViewPreview() {
    LearnTheme {
        HomeView()
    }
}
