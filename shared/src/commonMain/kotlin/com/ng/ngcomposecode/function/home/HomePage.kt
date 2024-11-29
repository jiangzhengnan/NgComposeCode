package com.ng.ngcomposecode.function.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomePage() {
	Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
		Text("首页")
	}
}

///**
// * Navigator与传统Activity不同，跨页面要使用ViewModel缓存数据，否则返回页面会重新请求
// */
//@Composable
//fun HomeMain() {
//	val navigator = LocalNavigator.current
//	val homeVM = viewModel(
//		modelClass = HomeViewModel::class,
//		creator = {
//			HomeViewModel()
//		})
//
//	Text("首页")
//}
//
