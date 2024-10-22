package com.example.na_regua_app.data.api

import com.example.na_regua_app.data.model.Funcionario
import retrofit2.Response
import retrofit2.http.GET

interface FuncionarioService {

    @GET("/funcionarios")
    suspend fun obterFuncionarios(
    ) : Response<List<Funcionario>>

}