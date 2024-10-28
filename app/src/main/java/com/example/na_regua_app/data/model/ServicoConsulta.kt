package com.example.na_regua_app.data.model

data class ServicoConsulta(
    val id: Int,
    val preco: Double,
    val descricao: String,
    val tipoServico: String,
    val tempoEstimado: String,
    val status: Boolean,
    val barbeiros: Set<String>
)