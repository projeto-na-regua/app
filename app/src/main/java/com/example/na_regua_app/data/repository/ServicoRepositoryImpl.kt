package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.api.ServicoService
import com.example.na_regua_app.data.model.Servico
import retrofit2.Response

class ServicoRepositoryImpl(
    private val service : ServicoService
) : ServicoRepository {

    override suspend fun obterServicosPorStatus(): Response<List<Servico>> {
        return service.obterServicosPorStatus(status = "active")
    }

    override suspend fun obterServicosCliente(idBarbearia: Int): Response<List<Servico>> {
        return service.obterServicosCliente(idBarbearia)
    }
}