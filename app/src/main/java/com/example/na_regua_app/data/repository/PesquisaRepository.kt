package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.model.Barbearia
import com.example.na_regua_app.data.model.BarbeariaPesquisa
import com.example.na_regua_app.data.model.Funcionario
import retrofit2.Response
import retrofit2.http.Query

interface PesquisaRepository {
    suspend fun listarBarbeariasWToken(
        servico: String,
        data: String,
        hora: String,
        raio: Int,
        token: String
    ) : Response<List<BarbeariaPesquisa>>

    suspend fun listarBarbeariasPorNome(
        token: String,
        nomeBarbearia: String,
    ) : Response<List<BarbeariaPesquisa>>
}