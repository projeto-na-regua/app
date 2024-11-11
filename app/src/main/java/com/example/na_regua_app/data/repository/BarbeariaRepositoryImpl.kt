package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.api.BarbeariaService
import com.example.na_regua_app.data.model.Barbearia
import retrofit2.Response

class BarbeariaRepositoryImpl(
    private val service : BarbeariaService
) : BarbeariaRepository {

    override suspend fun obterBarbearia() : Response<Barbearia> {
        return service.obterBarbearia()
    }

    override suspend fun obterBarbeariaCliente(idBarbearia: Int?): Response<Barbearia> {
        return service.obterBarbeariaCliente(idBarbearia)
    }

    override suspend fun atualizarBarbearia(novaBarbearia: Barbearia): Response<Barbearia> {
        return service.atualizarBarbearia(novaBarbearia)
    }

}