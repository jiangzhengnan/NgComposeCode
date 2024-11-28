package com.ng.ngcomposecode

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform