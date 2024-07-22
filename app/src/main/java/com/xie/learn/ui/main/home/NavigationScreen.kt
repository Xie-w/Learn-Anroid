package com.xie.learn.ui.main.home

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.utils.rememberDestinationsNavigator
import com.xie.learn.R
import com.xie.learn.ui.main.home.view.ChatsScreen
import com.xie.learn.ui.main.home.view.DiscoverScreen
import com.xie.learn.ui.main.home.view.HomeScreen
import com.xie.learn.ui.main.home.view.MineScreen
import com.xie.learn.ui.theme.LearnTheme

/**
 * @Author Xie
 * @Date   2024/7/18
 * @Des     navigation screen
 */
@Destination<RootGraph>
@Composable
fun NavigationScreen(navigator: DestinationsNavigator) {
    NavigationView(
        navigator = navigator,
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationView(
    navigator: DestinationsNavigator,
    onclick: () -> Unit = {}
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    val list = listOf(
        NavigationBottomBarItem.Home,
        NavigationBottomBarItem.Discover,
        NavigationBottomBarItem.Chats,
        NavigationBottomBarItem.Mine,
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                list.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = index == selectedIndex,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            if (index == selectedIndex) {
                                Icon(
                                    painter = painterResource(item.iconSelected),
                                    contentDescription = ""
                                )
                            } else {
                                Icon(
                                    painter = painterResource(item.iconUnselected),
                                    contentDescription = ""
                                )
                            }
                        },
                        label = {
                            Text(text = stringResource(id = item.label))
                        }
                    )
                }
            }
        }
    ) {
        when (selectedIndex) {
            0 -> HomeScreen(navigation = navigator)
            1 -> DiscoverScreen(navigation = navigator)
            2 -> ChatsScreen(navigation = navigator)
            3 -> MineScreen(navigation = navigator)
        }
    }
}

enum class NavigationBottomBarItem(
    val iconSelected: Int,
    val iconUnselected: Int,
    @StringRes val label: Int
) {
    Home(R.drawable.ic_home_selected, R.drawable.ic_home_unselected, R.string.home),
    Chats(R.drawable.ic_chats_selected, R.drawable.ic_chats_unselected, R.string.chats),
    Discover(R.drawable.ic_discover_selected, R.drawable.ic_discover_unselected, R.string.discover),
    Mine(R.drawable.ic_mine_selected, R.drawable.ic_mine_unselected, R.string.mine),
}

@Preview
@Composable
private fun NavigationViewPreview() {
    LearnTheme {
        NavigationView(navigator = rememberNavController().rememberDestinationsNavigator())
    }
}
