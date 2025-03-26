package com.health.model

data class HealthState(
    val physicalState: PhysicalState,
    val mentalState: MentalState,
    val date: String
)

data class PhysicalState(
    val weight: Double,
    val height: Double,
    val bmi: Double = weight / ((height / 100) * (height / 100))
)

data class MentalState(
    val pressure: Int, // 压力值 0-100
    val mood: Int     // 心情值 0-100
)

data class UserProfile(
    val name: String,
    val birthDate: String,
    val gender: String,
    val height: Double? = null,
    val targetWeight: Double? = null,
    val targetBMI: Double? = null
) 