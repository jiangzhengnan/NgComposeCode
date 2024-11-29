package com.ng.ngcomposecode.function.read

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.ng.ngcomposecode.service.BasicBean
import com.ng.ngcomposecode.service.NaviWrapper
import com.ng.ngcomposecode.utils.NgLog
import com.ng.ngcomposecode.utils.apiGet
import com.ng.ngcomposecode.utils.disposeFailed
import com.ng.ngcomposecode.utils.disposeSucceed
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class ReadViewModel : ViewModel() {

	private var _data: MutableState<List<NaviWrapper>?> = mutableStateOf(null)
	val data: State<List<NaviWrapper>?> = _data

	// 请求数据
	fun fetchData() {
		viewModelScope.launch {
			apiGet<BasicBean<List<NaviWrapper>>>("https://www.wanandroid.com/navi/json")
				.disposeSucceed {
					if (it.data?.isNotEmpty() == true) {
						NgLog.d("ReadViewModel ") { "fetchData success" }
						_data.value = it.data
					} else {
						NgLog.e("ReadViewModel") { "fetchData fail" }
					}
				}.disposeFailed {
					NgLog.e("ReadViewModel") { "fetchData fail:" + it.message.toString() }
				}
		}
	}
}