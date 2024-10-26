package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.model.Funcionario
import retrofit2.Response

interface FuncionarioRepository {
    suspend fun obterFuncionarios() : Response<List<Funcionario>>

    suspend fun obterFuncionariosCliente(idBarbearia: Int) : Response<List<Funcionario>>
}