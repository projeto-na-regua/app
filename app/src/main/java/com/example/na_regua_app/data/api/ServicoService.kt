package com.example.na_regua_app.data.api

import com.example.na_regua_app.data.model.NovoServico
import com.example.na_regua_app.data.model.Servico
import com.example.na_regua_app.data.model.ServicoConsulta
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ServicoService {

    @GET("api/servicos/list-by-status/{status}")
    suspend fun obterServicosPorStatus(
        @Path("status") status: String
    ) : Response<List<Servico>>

    @GET("api/servicos/client-side/{idBarbearia}")
    suspend fun obterServicosCliente(
        @Path("idBarbearia") idBarbearia: Int
    ) : Response<List<Servico>>

    @POST("api/servicos")
    suspend fun cadastrarServico(
        @Body nvServico: NovoServico
    ) : Response<ServicoConsulta>

}