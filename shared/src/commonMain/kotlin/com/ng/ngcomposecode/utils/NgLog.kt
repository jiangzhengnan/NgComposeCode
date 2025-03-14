package com.ng.ngcomposecode.utils

object NgLog {

    enum class LogLevel {
        DEBUG, INFO, WARNING, ERROR, NONE
    }

    // 在init中使用到的属性声明应该放在init方法前面，否则访问不到
    var currentLogLevel: LogLevel = LogLevel.NONE
        private set
    var isLoggingEnabled: Boolean = false
        private set

    // 首次调用 Ln 时执行
    init {
        enableLogging(true)
        setLogLevel(LogLevel.DEBUG)
    }

    fun setLogLevel(level: LogLevel) {
        currentLogLevel = level
    }

    fun enableLogging(enable: Boolean) {
        isLoggingEnabled = enable
    }

    fun d(tag: String, message: String) {
        if (isLoggingEnabled && currentLogLevel <= LogLevel.DEBUG) {
            print(LogLevel.DEBUG, tag, message)
        }
    }

    fun i(tag: String, message: String) {
        if (isLoggingEnabled && currentLogLevel <= LogLevel.INFO) {
            print(LogLevel.INFO, tag, message)
        }
    }

    fun w(tag: String, message: String) {
        if (isLoggingEnabled && currentLogLevel <= LogLevel.WARNING) {
            print(LogLevel.WARNING, tag, message)
        }
    }

    fun e(tag: String, message: String) {
        if (isLoggingEnabled && currentLogLevel <= LogLevel.ERROR) {
            print(LogLevel.ERROR, tag, message)
        }
    }
}

internal expect fun print(level: NgLog.LogLevel, tag: String, message: String)
