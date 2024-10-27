package com.example.na_regua_app.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun formatarDataHora(dataHora: String, apenasData: Boolean = false): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
    val dateTime = LocalDateTime.parse(dataHora, dateTimeFormatter)
    return if (apenasData) {
        dateTime.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) // Formato da data
    } else {
        dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) // Formato completo
    }
}
