package com.cxyzy.ktor.demo.utils


inline fun <R> R?.orElse(block: () -> R): R {
    return this ?: block()
}