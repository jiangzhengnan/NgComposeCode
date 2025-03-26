package com.health.viewmodel

import com.health.model.*
import com.health.mvi.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.*

class ReportViewModel(
    private val healthViewModel: HealthViewModel
) : MviModel<ReportState, ReportIntent, ReportEffect> {
    private val _state = MutableStateFlow(ReportState())
    override val state: StateFlow<ReportState> = _state

    override fun processIntent(intent: ReportIntent) {
        when (intent) {
            is ReportIntent.UpdateWeight -> updateWeight(intent.weight)
            is ReportIntent.UpdatePressure -> updatePressure(intent.pressure)
            is ReportIntent.UpdateMood -> updateMood(intent.mood)
            is ReportIntent.UpdateNote -> updateNote(intent.note)
            is ReportIntent.SaveReport -> saveReport()
            is ReportIntent.NavigateBack -> processEffect(ReportEffect.NavigateBack)
        }
    }

    override fun processEffect(effect: ReportEffect) {
        // 在实际应用中，这里会处理导航和显示提示等副作用
    }

    private fun updateWeight(weight: String) {
        _state.value = _state.value.copy(weight = weight)
    }

    private fun updatePressure(pressure: String) {
        _state.value = _state.value.copy(pressure = pressure)
    }

    private fun updateMood(mood: String) {
        _state.value = _state.value.copy(mood = mood)
    }

    private fun updateNote(note: String) {
        _state.value = _state.value.copy(note = note)
    }

    private fun saveReport() {
        val currentState = _state.value
        val weight = currentState.weight.toDoubleOrNull()
        val pressure = currentState.pressure.toIntOrNull()
        val mood = currentState.mood.toIntOrNull()

        if (weight == null || pressure == null || mood == null) {
            processEffect(ReportEffect.ShowError("请填写有效的数值"))
            return
        }

        val healthState = HealthState(
            physicalState = PhysicalState(
                weight = weight,
                height = 170.0 // 应该从用户配置中获取
            ),
            mentalState = MentalState(
                pressure = pressure,
                mood = mood
            ),
            date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.toString()
        )

        healthViewModel.addHealthState(healthState)
        processEffect(ReportEffect.ShowToast("记录已保存"))
        processEffect(ReportEffect.NavigateBack)
    }
} 