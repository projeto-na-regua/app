package com.example.na_regua_app.data.model

import java.time.LocalDateTime

data class Financa (
    val id: Int,
    val valor: Double,
    val dtLancamento: LocalDateTime,
    val barbearia: Barbearia,
    val despesa: Boolean
)