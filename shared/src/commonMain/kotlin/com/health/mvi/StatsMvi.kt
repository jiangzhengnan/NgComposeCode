package com.health.mvi

import com.health.model.HealthState

data class StatsState(
    val selectedTab: Int = 0,
    val timeRange: TimeRange = TimeRange.Month,
    val healthStates: List<HealthState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

enum class TimeRange {
    Day, Week, Month, Year
}

sealed class StatsIntent {
    data class SelectTab(val index: Int) : StatsIntent()
    data class SelectTimeRange(val range: TimeRange) : StatsIntent()
    object LoadData : StatsIntent()
    object NavigateBack : StatsIntent()
}

sealed class StatsEffect {
    data class ShowError(val message: String) : StatsEffect()
    object NavigateBack : StatsEffect()
    data class ShowToast(val message: String) : StatsEffect()
} 