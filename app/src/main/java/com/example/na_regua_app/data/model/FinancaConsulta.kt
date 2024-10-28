package com.example.na_regua_app.data.model

data class FinancaConsulta(
    val despesa: Double? = null,
    var lucro: Double? = null,
    var receita: Double? = null,
    private val _lucratividade: Double? = null,
    val servicos: Array<Array<String>> = arrayOf()
) {
    val lucratividade: Double?
        get() = if (_lucratividade?.isNaN() == true) 0.0 else _lucratividade
}
