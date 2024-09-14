package com.example.na_regua_app.classes

import androidx.compose.ui.graphics.Color

data class Dado(
    var value: Float,
    var label: String,
    var color: Color? = null,
)