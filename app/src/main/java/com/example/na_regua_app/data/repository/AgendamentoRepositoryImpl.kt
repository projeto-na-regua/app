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

    override suspend fun getAgendamentosPendentes(): Response<List<AgendamentoConsulta>> {
        return agendamentoService.getAgendamentosPendentes()
    }

    override suspend fun getAgendamentosAgendados(): Response<List<AgendamentoConsulta>> {
        return agendamentoService.getAgendamentosConfirmados()
    }

    override suspend fun getHistoricoCliente(): Response<List<AgendamentoConsulta>> {
        return agendamentoService.getHistoricoCliente()
    }

    override suspend fun updateStatusAgendamento(
        id: Int,
        status: String,
    ): Response<AgendamentoConsulta> {
        return agendamentoService.updateStatusAgendamento(id, status)
    }


}