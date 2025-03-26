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
import androidx.compose.material3.MaterialTheme
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
import com.ng.ngcomposecode.utils.pages
import com.ng.ngcomposecode.function.home.HomePage
import com.ng.ngcomposecode.function.my.MyPage
import com.ng.ngcomposecode.function.read.ReadPage
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import com.health.navigation.Navigation

const val PAGE_HEALTH_APP_ROUTE = "/health"

val LocalNavigator = staticCompositionLocalOf<Navigator> {
	error("No Navigator provided")
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
	MaterialTheme {
		// 路由层
		PreComposeApp {
			val navigator = rememberNavigator()
			CompositionLocalProvider(LocalNavigator provides navigator) {
				NavHost(
					navigator = navigator,
					navTransition = NavTransition(),
					initialRoute = PAGE_HEALTH_APP_ROUTE,
				) {
					// 健康应用路由
					scene(
						route = PAGE_HEALTH_APP_ROUTE,
						navTransition = NavTransition(),
					) {
						Navigation()
					}
					
					// 保留其他路由
					pages.forEach {
						scene(
							route = it.route,
							navTransition = NavTransition(),
							content = it.content
						)
					}
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