package com.example.na_regua_app.data.repository

import DashboardConsulta
import com.example.na_regua_app.data.api.FinancasService
import com.example.na_regua_app.data.model.FinancaConsulta
import com.example.na_regua_app.data.model.FinancaCriacao
import retrofit2.Response
import java.time.LocalDate

class FinancaRepositoryImpl (
    private val service: FinancasService
) : FinancaRepository {


    override suspend fun obterFinancas(
        qtdDias: Int,
        dataInicial: LocalDate,
        dataFinal: LocalDate,
    ): Response<FinancaConsulta> {
        return service.obterFinancas(qtdDias, dataInicial, dataFinal)
    }

    override suspend fun obterMetricasGerais(
        dateInicial: LocalDate,
        dateFinal: LocalDate,
        qtdDiasParaGrafico: Int,
    ): Response<DashboardConsulta> {
        return service.obterMetricasGerais(dateInicial, dateFinal, qtdDiasParaGrafico)
    }

    override suspend fun lancarValor(financaCriacao: FinancaCriacao): Response<Void> {
        return service.lancarValor(financaCriacao)
    }

}