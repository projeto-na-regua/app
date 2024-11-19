package com.example.na_regua_app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.na_regua_app.data.model.AgendamentoConsulta
import com.example.na_regua_app.data.model.AgendamentoCriacao
import com.example.na_regua_app.data.model.Funcionario
import com.example.na_regua_app.data.model.HorarioDisponivel
import com.example.na_regua_app.data.model.Servico
import com.example.na_regua_app.data.model.ServicoCardDTO
import com.example.na_regua_app.data.model.mapToServicoCardDTO
import com.example.na_regua_app.data.repository.AgendamentoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AgendamentoViewModel (
    private val agendamentoRepository : AgendamentoRepository
) : ViewModel() {

    private val _horarios = MutableStateFlow<List<HorarioDisponivel>>(emptyList())
    val horarios: StateFlow<List<HorarioDisponivel>> get() = _horarios

    private val _agendamentosPendentes = MutableStateFlow<List<AgendamentoConsulta>>(emptyList())
    val agendamentosPendentes: StateFlow<List<AgendamentoConsulta>> get() = _agendamentosPendentes

    private val _agendamentosAgendados = MutableStateFlow<List<AgendamentoConsulta>>(emptyList())
    val agendamentosAgendados: StateFlow<List<AgendamentoConsulta>> get() = _agendamentosAgendados

    private val _listaHistorico = MutableStateFlow<List<AgendamentoConsulta>>(emptyList())
    val listaHistorico: StateFlow<List<AgendamentoConsulta>> get() = _listaHistorico

    fun listarHorariosDisponiveis(barbeiro: Int, servico: Int, barbearia: Int, date: String) {
        viewModelScope.launch {
            try {

                val inputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDate = inputFormat.parse(date)?.let { outputFormat.format(it) }

                if (formattedDate != null) {
                    val horariosData = agendamentoRepository.listarHorariosDisponiveis(barbeiro, servico, barbearia, formattedDate)

                    if (horariosData.isSuccessful) {
                        val horarios = horariosData.body()
                        if (horarios != null) {
                            _horarios.value = horarios
                        }
                        Log.d("ServicoViewModel", "Dados de servicos: ${horariosData.body()}")
                    } else {
                        Log.e("PerfilBarbeariaViewModel", "Erro na resposta: ${horariosData.code()}")
                        Log.e("PerfilBarbeariaViewModel", "Erro ao obter os horarios disponiveis: ${horariosData.errorBody()?.string()}")
                        _horarios.value = emptyList()
                    }
                } else {
                    Log.e("AgendamentoViewModel", "Erro na conversão da data")
                }
            } catch (e: Exception) {
                Log.e("AgendamentoViewModel", "Erro ao obter os horarios disponiveis: ${e.message}")
                _horarios.value = emptyList()
            }
        }
    }

    fun adicionarAgendamento(date: String, time: String, servico: Int, barbeiro: Int, barbearia: Int, onResult: (Boolean) -> Unit) {
        val inputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

        try {
            val dataHoraStr = "$date $time"
            val dateParsed = inputFormat.parse(dataHoraStr)

            val currentTime = Date()
            if (dateParsed != null && dateParsed.after(currentTime)) {
                // Formate para o formato esperado pela API
                val formattedDataHora = outputFormat.format(dateParsed)

                val agendamentoCriacao = AgendamentoCriacao(dataHora = formattedDataHora, idServico = servico, idBarbeiro = barbeiro, idBarbearia = barbearia)

                viewModelScope.launch {
                    try {
                        val agendamentoData = agendamentoRepository.adicionarAgendamento(agendamentoCriacao)

                        if (agendamentoData.isSuccessful) {
                            Log.d("AgendamentoViewModel", "Dados do agendamento: ${agendamentoData.body()}")
                            onResult(true)
                        } else {
                            Log.e("AgendamentoViewModel", "Erro na resposta do agendamento: ${agendamentoData.code()}")
                            Log.e("AgendamentoViewModel", "Erro ao adicionar o agendamento: ${agendamentoData.errorBody()?.string()}")
                            onResult(false)
                        }
                    } catch (e: Exception) {
                        Log.e("AgendamentoViewModel", "Erro ao adicionar o agendamento: ${e.message}")
                        onResult(false)
                    }
                }
            } else {
                Log.e("AgendamentoViewModel", "A data/hora deve estar no futuro.")
                onResult(false)
            }
        } catch (e: Exception) {
            Log.e("AgendamentoViewModel", "Erro ao adicionar o agendamento: ${e.message}")
            onResult(false)
        }
    }

    fun listarAgendamentosPendentes() {
        viewModelScope.launch {
            try {
                val agendamentoData = agendamentoRepository.getAgendamentosPendentes()

                if (agendamentoData.isSuccessful) {
                    Log.d("AgendamentoViewModel", "Dados dos agendamentos: ${agendamentoData.body()}")
                    _agendamentosPendentes.value = agendamentoData.body()!!
                } else {
                    Log.e("AgendamentoViewModel", "Erro na resposta dos agendamentos: ${agendamentoData.code()}")
                    Log.e("AgendamentoViewModel", "Erro ao buscar os agendamentos: ${agendamentoData.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("AgendamentoViewModel", "Erro ao buscar os agendamentos: ${e.message}")
            }
        }
    }

    fun listarAgendamentosAgendados() {
        viewModelScope.launch {
            try {
                val agendamentoData = agendamentoRepository.getAgendamentosAgendados()

                if (agendamentoData.isSuccessful) {
                    Log.d("AgendamentoViewModel", "Dados dos agendamentos: ${agendamentoData.body()}")
                    _agendamentosAgendados.value = agendamentoData.body()!!
                } else {
                    Log.e("AgendamentoViewModel", "Erro na resposta dos agendamentos: ${agendamentoData.code()}")
                    Log.e("AgendamentoViewModel", "Erro ao buscar os agendamentos: ${agendamentoData.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("AgendamentoViewModel", "Erro ao buscar os agendamentos: ${e.message}")
            }
        }
    }

    fun listarHistoricoCliente(){
        viewModelScope.launch {
            try {
                val historicoData = agendamentoRepository.getHistoricoCliente()

                if (historicoData.isSuccessful) {
                    Log.d("AgendamentoViewModel", "Dados do historico: ${historicoData.body()}")
                    _listaHistorico.value = historicoData.body()!!
                } else {
                    Log.e("AgendamentoViewModel", "Erro na resposta do historico: ${historicoData.code()}")
                    Log.e("AgendamentoViewModel", "Erro ao buscar o historico: ${historicoData.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("AgendamentoViewModel", "Erro ao buscar o historico: ${e.message}")
            }
        }
    }

    fun updateStatusAgendamento(id: Int, status: String, onResult: (Boolean) -> Unit){
        viewModelScope.launch {
            try {
                val updateAgendamentoData = agendamentoRepository.updateStatusAgendamento(id, status)

                if (updateAgendamentoData.isSuccessful) {
                    Log.d("AgendamentoViewModel", "Dados do update do agendamento: ${updateAgendamentoData.body()}")
                    onResult(true)
                } else {
                    Log.e("AgendamentoViewModel", "Erro na resposta do update do agendamento: ${updateAgendamentoData.code()}")
                    Log.e("AgendamentoViewModel", "Erro ao atualizar o status do agendamento: ${updateAgendamentoData.errorBody()?.string()}")
                    onResult(false)
                }
            } catch (e: Exception) {
                Log.e("AgendamentoViewModel", "Erro ao atualizar o status do agendamento: ${e.message}")
                onResult(false)
            }
        }
    }

}