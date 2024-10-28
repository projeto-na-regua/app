package com.example.na_regua_app.data.repository

import DashboardConsulta
import com.example.na_regua_app.data.model.FinancaConsulta
import com.example.na_regua_app.data.model.FinancaCriacao
import retrofit2.Response
import java.time.LocalDate

interface FinancaRepository {

    suspend fun obterFinancas(qtdDias: Int, dataInicial: LocalDate, dataFinal: LocalDate) : Response<FinancaConsulta>

    suspend fun obterMetricasGerais(dateInicial: LocalDate, dateFinal: LocalDate, qtdDiasParaGrafico: Int) : Response<DashboardConsulta>

    suspend fun lancarValor(financaCriacao: FinancaCriacao) : Response<Void>

}