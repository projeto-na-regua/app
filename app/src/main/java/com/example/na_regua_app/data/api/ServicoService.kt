package com.example.na_regua_app.data.api

import com.example.na_regua_app.data.model.Servico
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ServicoService {

    @GET("/servicos/list-by-status/{status}")
    suspend fun obterServicosPorStatus(
        @Path("status") status: String
    ) : Response<List<Servico>>

    @GET("/servicos/client-side/{idBarbearia}")
    suspend fun obterServicosCliente(
        @Path("idBarbearia") idBarbearia: Int
    ) : Response<List<Servico>>

}