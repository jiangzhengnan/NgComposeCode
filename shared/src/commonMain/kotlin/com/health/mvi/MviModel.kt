package com.health.mvi

import kotlinx.coroutines.flow.StateFlow

interface MviModel<STATE : Any, INTENT : Any, EFFECT : Any> {
    val state: StateFlow<STATE>
    
    fun processIntent(intent: INTENT)
    
    fun processEffect(effect: EFFECT)
} 