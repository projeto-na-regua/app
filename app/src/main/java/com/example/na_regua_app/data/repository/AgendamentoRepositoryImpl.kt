package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.api.AgendamentoService
import com.example.na_regua_app.data.model.AgendamentoConsulta
import com.example.na_regua_app.data.model.AgendamentoCriacao
import com.example.na_regua_app.data.model.HorarioDisponivel
import retrofit2.Response

class AgendamentoRepositoryImpl(
    val agendamentoService: AgendamentoService
) : AgendamentoRepository {
    override suspend fun listarHorariosDisponiveis(
        barbeiro: Int,
        servico: Int,
        barbearia: Int,
        date: String,
    ): Response<List<HorarioDisponivel>> {
        return agendamentoService.listarHorariosDisponiveis(barbeiro, servico, barbearia, date)
    }

    override suspend fun adicionarAgendamento(nvAgendamentoCriacao: AgendamentoCriacao): Response<AgendamentoConsulta> {
        return agendamentoService.adicionarAgendamento(nvAgendamentoCriacao)
    }


}