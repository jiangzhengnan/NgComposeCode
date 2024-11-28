package com.ng.ngcomposecode

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "你好, ${platform.name}!"
    }
}