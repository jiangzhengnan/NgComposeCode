package com.health.viewmodel

import com.health.model.*
import com.health.mvi.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.*

class HealthViewModel : MviModel<MainState, MainIntent, MainEffect> {
    private val _state = MutableStateFlow<MainState>(MainState.Loading)
    override val state: StateFlow<MainState> = _state

    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile: StateFlow<UserProfile?> = _userProfile.asStateFlow()

    private val _healthStates = MutableStateFlow<List<HealthState>>(emptyList())
    val healthStates: StateFlow<List<HealthState>> = _healthStates.asStateFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        // 模拟加载初始数据
        _userProfile.value = UserProfile(
            name = "测试用户",
            birthDate = "1995-09-01",
            gender = "男",
            height = 170.0,
            targetWeight = 65.0,
            targetBMI = 22.0
        )
        
        _state.value = MainState.Success(
            currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
            userProfile = _userProfile.value,
            healthStates = _healthStates.value
        )
    }

    override fun processIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.LoadData -> loadInitialData()
            is MainIntent.ToggleView -> toggleView()
            is MainIntent.SelectDate -> selectDate(intent.date)
            is MainIntent.NavigateToReport -> processEffect(MainEffect.NavigateToReport)
            is MainIntent.NavigateToStats -> processEffect(MainEffect.NavigateToStats)
            is MainIntent.NavigateToSettings -> processEffect(MainEffect.NavigateToSettings)
        }
    }

    override fun processEffect(effect: MainEffect) {
        // 在实际应用中，这里会处理导航和显示提示等副作用
    }

    private fun toggleView() {
        val currentState = _state.value
        if (currentState is MainState.Success) {
            _state.value = currentState.copy(
                selectedView = if (currentState.selectedView == ViewType.Circle) ViewType.List else ViewType.Circle
            )
        }
    }

    private fun selectDate(date: LocalDate) {
        val currentState = _state.value
        if (currentState is MainState.Success) {
            _state.value = currentState.copy(currentDate = date)
        }
    }

    fun addHealthState(state: HealthState) {
        _healthStates.update { currentList ->
            listOf(state) + currentList
        }
        updateMainState()
    }

    fun updateUserProfile(profile: UserProfile) {
        _userProfile.value = profile
        updateMainState()
    }

    private fun updateMainState() {
        val currentState = _state.value
        if (currentState is MainState.Success) {
            _state.value = currentState.copy(
                userProfile = _userProfile.value,
                healthStates = _healthStates.value
            )
        }
    }
} 