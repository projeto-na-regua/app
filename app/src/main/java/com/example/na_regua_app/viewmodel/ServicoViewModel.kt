package com.example.na_regua_app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.na_regua_app.data.api.ServicoService
import com.example.na_regua_app.data.model.Barbearia
import com.example.na_regua_app.data.model.Servico
import com.example.na_regua_app.data.model.ServicoCardDTO
import com.example.na_regua_app.data.model.Usuario
import com.example.na_regua_app.data.model.mapToServicoCardDTO
import com.example.na_regua_app.data.repository.ServicoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class ServicoViewModel(
    private val servicoRepository: ServicoRepository
) : ViewModel() {

    private val _servicos = MutableStateFlow<List<ServicoCardDTO>>(emptyList())
    val servicos: StateFlow<List<ServicoCardDTO>> get() = _servicos

    fun obterServicosPorStatus(status: String = "active", idBarbearia: Int, isBarbeiro: Boolean) {
        viewModelScope.launch {
            val serviceData: Response<List<Servico>> = if (isBarbeiro) {
                servicoRepository.obterServicosPorStatus()
            } else {
                servicoRepository.obterServicosCliente(idBarbearia)
            }
            if (serviceData.isSuccessful) {
                val servicoCardDTOList = serviceData.body()?.map { servico ->
                    mapToServicoCardDTO(servico)
                } ?: emptyList()

                _servicos.value = servicoCardDTOList
                Log.d("ServicoViewModel", "Dados de servicos: ${serviceData.body()}")
            } else {
                Log.e("PerfilBarbeariaViewModel", "Erro na resposta: ${serviceData.code()}")
                Log.e("PerfilBarbeariaViewModel", "Erro ao obter barbearia: ${serviceData.errorBody()?.string()}")
                _servicos.value = emptyList()
            }
        }
    }
}