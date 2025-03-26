package com.health.viewmodel

import com.health.mvi.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.*

class StatsViewModel(
    private val healthViewModel: HealthViewModel
) : MviModel<StatsState, StatsIntent, StatsEffect> {
    private val _state = MutableStateFlow(StatsState())
    override val state: StateFlow<StatsState> = _state

    init {
        loadData()
    }

    override fun processIntent(intent: StatsIntent) {
        when (intent) {
            is StatsIntent.SelectTab -> selectTab(intent.index)
            is StatsIntent.SelectTimeRange -> selectTimeRange(intent.range)
            is StatsIntent.LoadData -> loadData()
            is StatsIntent.NavigateBack -> processEffect(StatsEffect.NavigateBack)
        }
    }

    override fun processEffect(effect: StatsEffect) {
        // 在实际应用中，这里会处理导航和显示提示等副作用
    }

    private fun selectTab(index: Int) {
        _state.value = _state.value.copy(selectedTab = index)
    }

    private fun selectTimeRange(range: TimeRange) {
        _state.value = _state.value.copy(timeRange = range)
        loadData()
    }

    private fun loadData() {
        val mainState = healthViewModel.state.value
        if (mainState is MainState.Success) {
            val now = Clock.System.now()
                .toLocalDateTime(TimeZone.currentSystemDefault())
                .date
            
            val filteredStates = mainState.healthStates.filter { state ->
                val stateDate = LocalDate.parse(state.date)
                val daysBetween = now.toEpochDays() - stateDate.toEpochDays()
                
                when (_state.value.timeRange) {
                    TimeRange.Day -> daysBetween <= 1
                    TimeRange.Week -> daysBetween <= 7
                    TimeRange.Month -> daysBetween <= 30
                    TimeRange.Year -> daysBetween <= 365
                }
            }
            
            _state.value = _state.value.copy(healthStates = filteredStates)
        }
    }
} 