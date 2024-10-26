package com.example.na_regua_app.data.api

import com.example.na_regua_app.data.model.Funcionario
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FuncionarioService {

    @GET("/funcionarios")
    suspend fun obterFuncionarios(
    ) : Response<List<Funcionario>>

    @GET("/funcionarios/client-side/{idBarbearia}")
    suspend fun obterFuncionariosCliente(
        @Path("idBarbearia") idBarbearia: Int
    ) : Response<List<Funcionario>>

}