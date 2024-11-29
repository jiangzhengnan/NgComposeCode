package com.ng.ngcomposecode.function

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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.ng.ngcomposecode.utils.PAGE_APP_HOME_ROUTE
import com.ng.ngcomposecode.utils.pages
import com.ng.ngcomposecode.function.home.HomePage
import com.ng.ngcomposecode.function.my.MyPage
import com.ng.ngcomposecode.function.read.ReadPage
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition

val LocalNavigator = staticCompositionLocalOf<Navigator> {
	error("Navigator not provided")
}

data class TabOptions(
	val index: Int,
	val title: String,
	val selectedIcon: ImageVector? = null,
	val unselectedIcon: ImageVector? = null,
	val content: @Composable () -> Unit
)


@Composable
fun App() {
	// 路由层
	PreComposeApp {
		// your app's content goes here
		val navigator = rememberNavigator()
		CompositionLocalProvider(LocalNavigator provides navigator) {
			NavHost(
				// Assign the navigator to the NavHost
				navigator = navigator,
				// Navigation transition for the scenes in this NavHost, this is optional
				navTransition = NavTransition(),
				// The start destination
				initialRoute = PAGE_APP_HOME_ROUTE,
			) {
				pages.forEach {
					scene(
						// Scene's route path
						route = it.route,
						// Navigation transition for this scene, this is optional
						navTransition = NavTransition(),
						content = it.content
					)
				}
			}
		}
	}

}

@Composable
fun AppHomePage() {
	var selectedItem by remember { mutableIntStateOf(0) }

	val tabs = listOf(
		TabOptions(
			index = 0,
			title = "主页",
			selectedIcon = Icons.Filled.Home,
			unselectedIcon = Icons.Outlined.Home,
			content = { HomePage() }
		),
		TabOptions(
			index = 1,
			title = "阅读",
			selectedIcon = Icons.Filled.Favorite,
			unselectedIcon = Icons.Outlined.FavoriteBorder,
			content = { ReadPage() }
		),
		TabOptions(
			index = 2,
			title = "我的",
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
			NavigationBar(containerColor = Color(0xFFf8f8f8)) {
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