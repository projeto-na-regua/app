package com.example.na_regua_app.data.model

data class Servico(
    val id: Int,
    val preco: Double,
    val descricao: String,
    val tipoServico: String,
    val tempoEstimado: Int,
    val status: Boolean,
    val barbeiros: List<String>
)

data class ServicoCardDTO(
    val id: Int,
    val preco: Double,
    val descricao: String,
    val tituloServico: String
)


fun mapToServicoCardDTO(servico: Servico): ServicoCardDTO {
    return ServicoCardDTO(
        id = servico.id,
        preco = servico.preco,
        descricao = servico.descricao,
        tituloServico = servico.tipoServico
    )
}
