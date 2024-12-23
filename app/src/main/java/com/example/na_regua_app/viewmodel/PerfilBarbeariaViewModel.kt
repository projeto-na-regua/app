package com.example.na_regua_app.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.example.na_regua_app.data.model.Barbearia
import com.example.na_regua_app.data.model.DiaSemana
import com.example.na_regua_app.data.repository.BarbeariaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.Response

class PerfilBarbeariaViewModel(
    private val barbeariaRepository: BarbeariaRepository,
) : ViewModel() {

    private val _barbearia = MutableStateFlow<Barbearia?>(null)
    val barbearia: StateFlow<Barbearia?> get() = _barbearia

    fun obterBarbearia(isBarbeiro: Boolean, idBarbearia: Int) {
        viewModelScope.launch {
            try {
                val barbeariaData: retrofit2.Response<Barbearia> = if (isBarbeiro) {
                    barbeariaRepository.obterBarbearia()
                } else {
                    barbeariaRepository.obterBarbeariaCliente(idBarbearia)
                }
                if (barbeariaData.isSuccessful) {
                    _barbearia.value = barbeariaData.body()
                    Log.d("PerfilBarbeariaViewModel", "Dados da barbearia: ${barbeariaData.body()}")
                } else {
                    Log.e("PerfilBarbeariaViewModel", "Erro na resposta: ${barbeariaData.code()}")
                    Log.e("PerfilBarbeariaViewModel", "Erro ao obter barbearia: ${barbeariaData.errorBody()?.string()}")
                    _barbearia.value = null
                }
            } catch (e: Exception) {
                Log.e("PerfilBarbeariaViewModel", "Erro ao obter barbearia: ${e.message}")
                _barbearia.value = null
            }
        }
    }

    fun atualizarHorario(
        barbearia: Barbearia,
        novaListaDeHorarios: List<DiaSemana>,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val barbeariaAtualizada = barbearia.copy(emailNegocio = "example@example.com", diaSemanas = novaListaDeHorarios)

                // Imprimir os horários de maneira legível
                novaListaDeHorarios.forEach { dia ->
                    Log.d("Horario", "Dia: ${dia.nome}, Abertura: ${dia.horaAbertura}, Fechamento: ${dia.horaFechamento}")
                }

                val barbeariaData = barbeariaRepository.atualizarBarbearia(barbeariaAtualizada)

                if (barbeariaData.isSuccessful) {
                    Log.d("PerfilBarbeariaViewModel", "Dados da barbearia atualizada: ${barbeariaData.body()}")
                    onResult(true)
                } else {
                    Log.d("PerfilBarbeariaViewModel", "Erro na resposta da atualização: ${barbeariaData.code()}")
                    Log.d("PerfilBarbeariaViewModel", "Erro na atualização: ${barbeariaData.errorBody()?.string()}")
                    onResult(false)
                }
            } catch (e: Exception) {
                Log.e("PerfilBarbeariaViewModel", "Erro ao atualizar a barbearia: ${e.message}")
                onResult(false)
            }
        }
    }


}
