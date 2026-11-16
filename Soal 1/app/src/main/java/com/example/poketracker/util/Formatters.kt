package com.example.poketracker.util

import java.util.Locale

fun Double?.toUsd(): String =
    if (this == null) "N/A" else "\$" + String.format(Locale.US, "%.2f", this)

fun Int?.orDash(): String = this?.toString() ?: "-"

fun String?.orDash(): String = if (this.isNullOrBlank()) "-" else this
