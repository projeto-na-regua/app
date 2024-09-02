package com.example.na_regua_app.classes

import java.time.LocalDateTime

data class Notificacao(
    val id: Int,
    val nome: String,
    var tipo: String,
    val dataHora: LocalDateTime,
    val descricao: String,
    var visto: Boolean
)
