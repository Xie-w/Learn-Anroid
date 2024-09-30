package com.xie.learn.ui.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.xie.learn.ui.theme.LearnTheme

/**
 * @Author Xie
 * @Date   2024/7/22
 * @Des
 */
@Destination<RootGraph>
@Composable
fun LoginScreen(navigator: DestinationsNavigator) {
    LoginView()
}

@Composable
fun LoginView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "login")
    }
}

@Preview
@Composable
private fun LoginViewPreview() {
    LearnTheme {
        LoginView()
    }
}