package com.ng.ngcomposecode.utils

import android.util.Log

internal actual fun print(level: NgLog.LogLevel, tag: String, message: String) {
    when (level) {
        NgLog.LogLevel.INFO -> Log.i(tag, message)
        NgLog.LogLevel.DEBUG -> Log.d(tag, message)
        NgLog.LogLevel.WARNING -> Log.w(tag, message)
        NgLog.LogLevel.ERROR -> Log.e(tag, message)
        NgLog.LogLevel.NONE -> Log.i(tag, message)
    }
}