package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.model.NovoServico
import com.example.na_regua_app.data.model.Servico
import com.example.na_regua_app.data.model.ServicoConsulta
import retrofit2.Response


interface ServicoRepository {
    suspend fun obterServicosPorStatus(status: String) : Response<List<Servico>>

    suspend fun obterServicosCliente(idBarbearia: Int) : Response<List<Servico>>

    suspend fun cadastrarServico(novoServico: NovoServico) : Response<ServicoConsulta>
}