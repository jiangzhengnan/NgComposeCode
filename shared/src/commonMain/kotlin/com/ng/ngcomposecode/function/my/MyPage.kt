package com.ng.ngcomposecode.function.my

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.dixon.app.kmmsample.MR
import com.ng.ngcomposecode.BottomNavBarHeight
import com.ng.ngcomposecode.utils.ArrowRightListItem
import com.ng.ngcomposecode.utils.NgLog
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun MyPage() {
	Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
		Text("我的")
	}

	val scope = rememberCoroutineScope()
	Column(
		modifier = Modifier.padding(bottom = BottomNavBarHeight).fillMaxSize()
			.background(color = Color.White)
	) {
		//AppToolsBar(title = "我的")
		// 头像 + 昵称
		Box(
			modifier = Modifier.fillMaxWidth().height(180.dp).background(color = Color(0xFF3A4B5D))
		) {
			Column(
				modifier = Modifier.fillMaxWidth().padding(top = 30.dp)
			) {
				AsyncImage(
					modifier =
					Modifier.size(80.dp)
						.clip(CircleShape)
						.align(Alignment.CenterHorizontally),
					model = "https://avatars.githubusercontent.com/u/14868338?v=4",
					contentDescription = null,
					contentScale = ContentScale.FillWidth
				)

				Text(
					text = "Pumpkin",
					modifier = Modifier.align(Alignment.CenterHorizontally),
					fontSize = 14.sp,
					color = Color.White
				)
			}
		}

		//Github主页
		ArrowRightListItem(
			iconRes = Icons.Default.Home, title = "Github 主页"
		) {
			NgLog.i("MyPage") { "跳转github" }
//			mainViewModel.handIntent(
//				MainViewAction.OpenWeb(
//					WebData(
//						"Github",
//						"https://github.com/jiangzhengnan"
//					)
//				)
//			)
		}

		// 重置进度（清除缓存）
		ArrowRightListItem(
			iconRes = Icons.Default.Settings, title = "重置进度"
		) { }

		// 切换主题
		ArrowRightListItem(
			iconRes = Icons.Default.Build, title = stringResource(MR.strings.change_theme)
		) {
			//mainViewModel.handIntent(MainViewAction.ChangeTheme)
		}

	}
}