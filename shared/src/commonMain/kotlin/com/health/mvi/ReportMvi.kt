package com.health.mvi

data class ReportState(
    val weight: String = "",
    val pressure: String = "",
    val mood: String = "",
    val note: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class ReportIntent {
    data class UpdateWeight(val weight: String) : ReportIntent()
    data class UpdatePressure(val pressure: String) : ReportIntent()
    data class UpdateMood(val mood: String) : ReportIntent()
    data class UpdateNote(val note: String) : ReportIntent()
    object SaveReport : ReportIntent()
    object NavigateBack : ReportIntent()
}

sealed class ReportEffect {
    data class ShowError(val message: String) : ReportEffect()
    object NavigateBack : ReportEffect()
    data class ShowToast(val message: String) : ReportEffect()
} 