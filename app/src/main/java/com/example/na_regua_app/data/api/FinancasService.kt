package com.example.na_regua_app.data.api

import DashboardConsulta
import com.example.na_regua_app.data.model.FinancaConsulta
import com.example.na_regua_app.data.model.FinancaCriacao
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.time.LocalDate

interface FinancasService {

    @GET("financas")
    suspend fun obterFinancas(
        @Query("qtdDias") qtdDias: Int,
        @Query("dataInicial") dataInicial: LocalDate,
        @Query("dataFinal") dataFinal: LocalDate
    ) : Response<FinancaConsulta>

    @GET("agendamentos/dashboard/metricas")
    suspend fun obterMetricasGerais(
        @Query("dateInicial") dateInicial: LocalDate,
        @Query("dateFinal") dateFinal: LocalDate,
        @Query("qtdDiasParaGrafico") qtdDiasParaGrafico: Int,
    ) : Response<DashboardConsulta>

    @POST("financas/lancar-valor")
    suspend fun lancarValor(
        @Body financaCriacao: FinancaCriacao
    ) : Response<Void>

}