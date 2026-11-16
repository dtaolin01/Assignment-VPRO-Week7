package com.example.musicartist.util

import java.util.Locale

// "318000" (ms) -> "05:18"
fun String?.toDuration(): String {
    val ms = this?.toLongOrNull() ?: return "--:--"
    val totalSeconds = ms / 1000
    return String.format(Locale.US, "%02d:%02d", totalSeconds / 60, totalSeconds % 60)
}

fun Int.twoDigits(): String = String.format(Locale.US, "%02d", this)
