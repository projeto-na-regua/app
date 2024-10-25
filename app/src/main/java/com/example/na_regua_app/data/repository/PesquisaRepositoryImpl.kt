package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.api.PesquisaService
import com.example.na_regua_app.data.model.BarbeariaPesquisa
import retrofit2.Response

class PesquisaRepositoryImpl (
    private val service : PesquisaService
    ) : PesquisaRepository {

    override suspend fun listarBarbeariasWToken(
        servico: String,
        data: String,
        hora: String,
        raio: Int,
        token: String,
    ): Response<List<BarbeariaPesquisa>> {
        return service.listarBarbeariasWToken(token, servico, data, hora, raio)
    }

    override suspend fun listarBarbeariasPorNome(
        token: String,
        nomeBarbearia: String,
    ): Response<List<BarbeariaPesquisa>> {
        return service.listarBarbeariasPorNome(token, nomeBarbearia)
    }
}