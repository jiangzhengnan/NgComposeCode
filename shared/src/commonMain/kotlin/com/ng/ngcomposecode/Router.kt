package com.ng.ngcomposecode

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.BackStackEntry
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator


data class Page(
	val route: String,
	val content: @Composable AnimatedContentScope.(BackStackEntry) -> Unit
)

const val PAGE_HOME_ROUTE = "/home_main"
const val PAGE_READ_ROUTE = "/read_main"
const val PAGE_MY_ROUTE = "/my_main"

val pages = listOf(
	Page(PAGE_HOME_ROUTE) {
		//HomeMain()
	},
//	Page(PAGE_IMAGE_DETAIL_ROUTE) { backStackEntry ->
//		val resource: String? = backStackEntry.query("resource")
//		ImageDetail(resource)
//	}
)


fun Navigator.Builder(route: String) = NavigatorBuilder(this, route)

fun NavigatorBuilder.put(key: String, value: Any?) = this.apply {
	value?.let {
		params[key] = it
	}
	// 为空则直接忽略 传xx=null时，backStackEntry.query会获取到字符串null，导致一些问题
}

fun NavigatorBuilder.put(params: Map<String, Any>) = this.apply {
	this.params.putAll(params)
}

fun NavigatorBuilder.options(options: NavOptions) = this.apply {
	this.options = options
}

fun NavigatorBuilder.build() {
	var realRoute = route
	if (params.isNotEmpty()) {
		realRoute += "?"
		params.forEach {
			realRoute += "${it.key}=${it.value}&"
		}
		realRoute.substring(0, realRoute.length - 1)
	}
	navigator.navigate(realRoute, options)
}


class NavigatorBuilder(val navigator: Navigator, val route: String) {
	val params: MutableMap<String, Any> = mutableMapOf()
	var options: NavOptions? = null
}