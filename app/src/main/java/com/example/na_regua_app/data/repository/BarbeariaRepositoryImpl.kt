package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.api.BarbeariaService
import com.example.na_regua_app.data.model.Barbearia
import retrofit2.Response

class BarbeariaRepositoryImpl(
    private val service : BarbeariaService
) : BarbeariaRepository {

    override suspend fun obterBarbearia(idBarbearia: Int) : Response<Barbearia> {
        return service.obterBarbearia(idBarbearia)
    }

    override suspend fun obterBarbeariaCliente(idBarbearia: Int): Response<Barbearia> {
        return service.obterBarbeariaCliente(idBarbearia)
    }

}