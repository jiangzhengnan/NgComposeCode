package com.ng.ngcomposecode.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
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
import androidx.compose.ui.graphics.vector.ImageVector
import com.ng.ngcomposecode.ui.home.HomePage
import com.ng.ngcomposecode.ui.my.MyPage
import com.ng.ngcomposecode.ui.read.ReadPage

//val LocalNavigator = staticCompositionLocalOf<Navigator> {
//	error("Navigator not provided")
//}

data class TabOptions(
	val index: Int,
	val title: String,
	val selectedIcon: ImageVector? = null,
	val unselectedIcon: ImageVector? = null,
	val content: @Composable () -> Unit
)


@Composable
fun MainPage() {
	var selectedItem by remember { mutableIntStateOf(0) }

	val tabs = listOf(
		TabOptions(
			index = 0,
			title = "Home",
			selectedIcon = Icons.Filled.Home,
			unselectedIcon = Icons.Outlined.Home,
			content = { HomePage() }
		),
		TabOptions(
			index = 1,
			title = "Read",
			selectedIcon = Icons.Filled.Favorite,
			unselectedIcon = Icons.Outlined.FavoriteBorder,
			content = { ReadPage() }
		),
		TabOptions(
			index = 2,
			title = "My",
			selectedIcon = Icons.Filled.Star,
			unselectedIcon = Icons.Outlined.Star,
			content = { MyPage() }
		)
	)

	Scaffold(
		floatingActionButtonPosition = FabPosition.End,
		floatingActionButton = {
			ExtendedFloatingActionButton(onClick = { /* fab click handler */ }) { Text("Inc") }
		},
		content = { innerPadding ->
			tabs[selectedItem].content()
		},
		bottomBar = {
			NavigationBar {
				tabs.forEachIndexed { index, item ->
					NavigationBarItem(
						icon = {
							Icon(
								imageVector = (if (selectedItem == index) item.selectedIcon else
									item.unselectedIcon)!!,
								contentDescription = item.title
							)
						},
						label = { Text(item.title) },
						selected = selectedItem == index,
						onClick = { selectedItem = index }
					)
				}
			}
		}
	)

}
