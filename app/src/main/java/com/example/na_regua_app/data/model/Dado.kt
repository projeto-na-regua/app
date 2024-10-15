package com.example.na_regua_app.data.model

import androidx.compose.ui.graphics.Color

data class Dado(
    var value: Float,
    var label: String,
    var color: Color? = null,
)