package com.example.na_regua_app.data.model

import androidx.compose.ui.graphics.Color

data class ItemMenuDropDown(
    var name: String,
    var nameAfter: String? = null,
    var colorBefore: Color? = null,
    var colorAfter: Color? = null,
    var action: (() -> Unit)? = null // Define que a função não retorna nulo
)