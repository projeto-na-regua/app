package com.example.na_regua_app.data.api

import com.example.na_regua_app.data.model.AgendamentoConsulta
import com.example.na_regua_app.data.model.AgendamentoCriacao
import com.example.na_regua_app.data.model.BarbeariaPesquisa
import com.example.na_regua_app.data.model.HorarioDisponivel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface AgendamentoService {

    @GET("api/agendamentos/list-horarios-disponiveis")
    suspend fun listarHorariosDisponiveis(
        @Query("barbeiro") barbeiro: Int,
        @Query("servico") servico: Int,
        @Query("barbearia") barbearia: Int,
        @Query("date") date: String,
    ): Response<List<HorarioDisponivel>>

    @POST("api/agendamentos")
    suspend fun adicionarAgendamento(
        @Body nvAgendamento: AgendamentoCriacao
    ): Response<AgendamentoConsulta>

    @GET("api/agendamentos/list-all-by-status/Pendente")
    suspend fun getAgendamentosPendentes() : Response<List<AgendamentoConsulta>>

    @GET("api/agendamentos/list-all-by-status/Agendado")
    suspend fun getAgendamentosConfirmados() : Response<List<AgendamentoConsulta>>

    @GET("api/agendamentos/historico")
    suspend fun getHistoricoCliente() : Response<List<AgendamentoConsulta>>

    @PUT("api/agendamentos/{id}/{status}")
    suspend fun updateStatusAgendamento(
        @Path("id") id: Int,
        @Path("status") status: String
    ) : Response<AgendamentoConsulta>

}