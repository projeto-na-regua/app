package com.example.na_regua_app.data.model

data class DiaSemana(
    val id: Int,
    val nome: String,
    var horaAbertura: String? = null, // Definindo padrão como null
    var horaFechamento: String? = null // Definindo padrão como null
)


fun criarListaDiaSemana(
    diasSemana: List<DiaSemana>
): List<DiaSemana> {
    return diasSemana.map { dia ->
        DiaSemana(
            id = dia.id,
            nome = dia.nome,
            horaAbertura = dia.horaAbertura,
            horaFechamento = dia.horaFechamento
        )
    }
}

fun expandDayName(shortName: String): String {
    return when (shortName.uppercase()) {
        "SEG" -> "Segunda-feira"
        "TER" -> "Terça-feira"
        "QUA" -> "Quarta-feira"
        "QUI" -> "Quinta-feira"
        "SEX" -> "Sexta-feira"
        "SAB" -> "Sábado"
        "DOM" -> "Domingo"
        else -> shortName
    }
}

