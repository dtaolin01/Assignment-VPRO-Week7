package com.example.musicartist.util

import com.example.musicartist.repository.DataNotFoundException
import com.google.gson.JsonParseException
import retrofit2.HttpException
import java.io.IOException

fun Throwable.toUserMessage(): String = when (this) {
    is DataNotFoundException -> message ?: "Data tidak ditemukan"
    is HttpException ->
        if (code() == 429) "Batas request tercapai (HTTP 429), coba lagi nanti"
        else "Server mengembalikan error HTTP ${code()}"
    is JsonParseException -> "Respons server tidak valid (kemungkinan batas request tercapai)"
    is IOException -> localizedMessage ?: "Koneksi gagal"
    else -> localizedMessage ?: "Terjadi kesalahan tidak diketahui"
}
