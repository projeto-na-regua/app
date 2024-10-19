package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.model.Servico
import retrofit2.Response


interface ServicoRepository {
    suspend fun obterServicosPorStatus() : Response<List<Servico>>
}