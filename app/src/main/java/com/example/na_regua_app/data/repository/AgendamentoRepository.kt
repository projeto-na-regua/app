package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.model.AgendamentoConsulta
import com.example.na_regua_app.data.model.AgendamentoCriacao
import com.example.na_regua_app.data.model.HorarioDisponivel
import retrofit2.Response

interface AgendamentoRepository {

    suspend fun listarHorariosDisponiveis(barbeiro: Int, servico: Int, barbearia: Int, date: String) : Response<List<HorarioDisponivel>>

    suspend fun adicionarAgendamento(nvAgendamentoCriacao: AgendamentoCriacao) : Response<AgendamentoConsulta>

    suspend fun getAgendamentosPendentes() : Response<List<AgendamentoConsulta>>

    suspend fun getAgendamentosAgendados() : Response<List<AgendamentoConsulta>>

    suspend fun getHistoricoCliente() : Response<List<AgendamentoConsulta>>

    suspend fun updateStatusAgendamento(id: Int, status: String) : Response<AgendamentoConsulta>

}