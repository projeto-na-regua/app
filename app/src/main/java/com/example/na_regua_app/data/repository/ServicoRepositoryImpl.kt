package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.api.ServicoService
import com.example.na_regua_app.data.model.NovoServico
import com.example.na_regua_app.data.model.Servico
import com.example.na_regua_app.data.model.ServicoConsulta
import retrofit2.Response

class ServicoRepositoryImpl(
    private val service : ServicoService
) : ServicoRepository {

    override suspend fun obterServicosPorStatus(status: String): Response<List<Servico>> {
        return service.obterServicosPorStatus(status)
    }

    override suspend fun obterServicosCliente(idBarbearia: Int): Response<List<Servico>> {
        return service.obterServicosCliente(idBarbearia)
    }

    override suspend fun cadastrarServico(novoServico: NovoServico): Response<ServicoConsulta> {
        return service.cadastrarServico(novoServico)
    }
}