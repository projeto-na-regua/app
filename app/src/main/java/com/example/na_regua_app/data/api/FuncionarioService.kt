package com.example.na_regua_app.data.api

import com.example.na_regua_app.data.model.BarbeiroConsulta
import com.example.na_regua_app.data.model.Funcionario
import com.example.na_regua_app.data.model.NovoBarbeiro
import com.example.na_regua_app.data.model.NovoServico
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FuncionarioService {

    @GET("funcionarios")
    suspend fun obterFuncionarios(
    ) : Response<List<Funcionario>>

    @GET("funcionarios/client-side/{idBarbearia}")
    suspend fun obterFuncionariosCliente(
        @Path("idBarbearia") idBarbearia: Int
    ) : Response<List<Funcionario>>

    @POST("funcionarios/criar")
    suspend fun cadastrarBarbeiro(
        @Body barbeiro: NovoBarbeiro,
    ) : Response<BarbeiroConsulta>

}