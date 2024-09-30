package com.xie.learn.ui.main.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.xie.learn.ui.theme.LearnTheme
import com.xie.learn.ui.theme.Teal700

/**
 * @Author Xie
 * @Date   2024/7/17
 * @Des   main item view
 */
@Composable
fun MainItemView(title: String, onClicked: () -> Unit) {
    Card(
        elevation = CardDefaults
            .cardElevation(
                defaultElevation = 8.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = Teal700,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp, 0.dp, 4.dp, 0.dp),

        onClick = {
            onClicked()
        },
    ) {
        Text(
            text = title,
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth(),
            color = Color.White,
            textAlign = TextAlign.Center,
            maxLines = 1,
            fontSize = 20.sp,
        )
    }
}

@Preview(device = "id:pixel_5")
@Composable
private fun PreviewMainItemView() {
    LearnTheme {
        MainItemView(title = "title", onClicked = { })
    }
}
