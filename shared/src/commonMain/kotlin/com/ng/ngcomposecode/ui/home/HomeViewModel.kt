package com.ng.ngcomposecode.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class HomeViewModel : ViewModel() {

//    private var _data: MutableState<DogData?> = mutableStateOf(null)
//    val data: State<DogData?> = _data
//
//    // 请求数据
//    fun fetchData() {
//        viewModelScope.launch {
//            apiGet<List<DogData>>("https://api.thecatapi.com/v1/images/search?api_key=live_UrfFVwYtMzbL2mvoAW0YdvpEXTbfeFLNIFWsfUJ98OBTZC7EmGguo4noxwpv7tXu")
//                .disposeSucceed {
//                    _data.value = it.first()
//                }.disposeFailed {
//                    Logger.e("HomeViewModel#fetchData") { it.message.toString() }
//                }
//        }
//    }
}