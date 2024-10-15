package com.example.na_regua_app.data.model

data class DiaSemana(
    val id: Int,
    val nome: String,
    var horaAbertura: String? = "00:00:00",
    var horaFechamento: String? = "00:00:00",
    var status: Boolean
)

fun criarListaDiaSemana(): List<DiaSemana>{
    return listOf<DiaSemana>(
        DiaSemana(
            id = 1,
            nome = expandDayName("DOM"),
            status = false
        ),
        DiaSemana(
            id = 2,
            nome = expandDayName("SEG"),
            horaAbertura = "09:30:00",
            horaFechamento = "18:30:00",
            status = true
        ),
        DiaSemana(
            id = 2,
            nome = expandDayName("TER"),
            horaAbertura = "09:30:00",
            horaFechamento = "18:30:00",
            status = true
        ), DiaSemana(
            id = 2,
            nome = expandDayName("QUA"),
            horaAbertura = "09:30:00",
            horaFechamento = "18:30:00",
            status = true
        ), DiaSemana(
            id = 2,
            nome = expandDayName("QUI"),
            horaAbertura = "09:30:00",
            horaFechamento = "18:30:00",
            status = true
        ), DiaSemana(
            id = 2,
            nome = expandDayName("SEX"),
            horaAbertura = "09:30:00",
            horaFechamento = "18:30:00",
            status = true
        ), DiaSemana(
            id = 2,
            nome = expandDayName("SAB"),
            horaAbertura = "09:00:00",
            horaFechamento = "13:00:00",
            status = true
        )
    )
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
