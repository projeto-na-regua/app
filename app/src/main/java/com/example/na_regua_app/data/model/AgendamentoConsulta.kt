package com.example.na_regua_app.data.model

import java.time.LocalDateTime

data class AgendamentoConsulta(
    val id: Int,
    val dataHora: LocalDateTime,
    val tipoServico: String,
    val descricao: String,
    val valorServico: Double,
    val nomeCliente: String,
    val nomeBarbeiro: String,
    val nomeNegocio: String,
    val imgPerfilBarbearia: String,
    val status: String,
    val enderecoBarbearia: EnderecoConsulta,
    val tempoEstimado: Int,
    val dataHoraPrevista: LocalDateTime,
    val avaliacao: Double,
    val comentario: String?
    )
