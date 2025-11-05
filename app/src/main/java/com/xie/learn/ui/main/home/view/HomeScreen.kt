package com.xie.learn.ui.main.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.xie.learn.ui.theme.LearnTheme
import com.xie.learn.ui.theme.PurpleGrey80
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @Author Xie
 * @Date   2024/7/22
 * @Des    home screen.
 */
@Destination<RootGraph>
@Composable
fun HomeScreen(
    navigation: DestinationsNavigator
) {
    HomeView()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView() {
    var refreshing by remember { mutableStateOf(false) }
    var loadingMore by remember { mutableStateOf(false) }
    var items by remember { mutableStateOf((1..20).map { "Item $it" }) }

    val pullToRefreshState = rememberPullToRefreshState()
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    PullToRefreshBox(
        isRefreshing = refreshing,
        onRefresh = {
            refreshing = true
            coroutineScope.launch {
                delay(timeMillis = 1500)
                items = (1..20).map { "Refreshed Item $it" }
                refreshing = false
            }
        },
        modifier = Modifier.background(
            color = PurpleGrey80
        ),
        state = pullToRefreshState
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            items(items) { item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp)
                )
                HorizontalDivider(thickness = 0.5.dp)
            }

            if (loadingMore) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = listState) {
        snapshotFlow {
            val layoutInfo = listState.layoutInfo
            val lastVisible = layoutInfo.visibleItemsInfo.lastOrNull()?.index
            lastVisible == layoutInfo.totalItemsCount - 1
        }.collect { isAtBottom ->
            if (isAtBottom && !refreshing && !loadingMore) {
                loadingMore = true
                coroutineScope.launch {
                    delay(timeMillis = 1500)
                    val nextItems = (items.size + 1..items.size + 10).map { "Loaded Item $it" }
                    items = items + nextItems
                    loadingMore = false
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeViewPreview() {
    LearnTheme {
        HomeView()
    }
}
