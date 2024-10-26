package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.api.FuncionarioService
import com.example.na_regua_app.data.model.Funcionario
import retrofit2.Response

class FuncionarioRepositoryImpl(
    private val service : FuncionarioService
) : FuncionarioRepository {

    override suspend fun obterFuncionarios(): Response<List<Funcionario>> {
        return service.obterFuncionarios()
    }

    override suspend fun obterFuncionariosCliente(idBarbearia: Int): Response<List<Funcionario>> {
        return service.obterFuncionariosCliente(idBarbearia)
    }
}