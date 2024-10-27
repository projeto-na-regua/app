package com.example.na_regua_app.data.model

data class NovoServico(
        val preco: Double,
        val descricao: String,
        val tipoServico: String,
        val tempoEstimado: Int,
        val barbeirosEmails: List<String>
)