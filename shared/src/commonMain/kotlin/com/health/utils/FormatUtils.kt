package com.health.utils

import kotlinx.datetime.*
import kotlin.math.round

object FormatUtils {
    fun formatDouble(value: Double): String {
        // 将数字四舍五入到一位小数
        val rounded = round(value * 10) / 10
        return rounded.toString()
    }

    fun formatDate(date: LocalDate): String {
        return "${date.year}年${date.monthNumber}月"
    }

    fun getCurrentDate(): String {
        return Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date
            .toString()
    }
} 