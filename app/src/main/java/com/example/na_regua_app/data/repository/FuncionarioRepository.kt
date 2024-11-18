package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.model.BarbeiroConsulta
import com.example.na_regua_app.data.model.Funcionario
import com.example.na_regua_app.data.model.NovoBarbeiro
import retrofit2.Response

interface FuncionarioRepository {

    suspend fun obterFuncionarios() : Response<List<Funcionario>>

    suspend fun obterFuncionariosCliente(idBarbearia: Int) : Response<List<Funcionario>>

    suspend fun cadastrarFuncionario(novoBarbeiro: NovoBarbeiro) : Response<BarbeiroConsulta>

}