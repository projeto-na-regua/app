package com.example.na_regua_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.na_regua_app.data.api.ServicoService
import com.example.na_regua_app.data.model.Servico
import com.example.na_regua_app.data.model.ServicoCardDTO
import com.example.na_regua_app.data.model.Usuario
import com.example.na_regua_app.data.model.mapToServicoCardDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ServicoViewModel(
    private val servicoService: ServicoService
) : ViewModel() {

    private val _servicos = MutableStateFlow<List<ServicoCardDTO>>(emptyList())
    val servicos: StateFlow<List<ServicoCardDTO>> get() = _servicos

    fun obterServicosPorStatus(status: String = "active") {
        viewModelScope.launch {
            val response = servicoService.obterServicosPorStatus(status)
            if (response.isSuccessful) {
                val servicoCardDTOList = response.body()?.map { servico ->
                    mapToServicoCardDTO(servico)
                } ?: emptyList()

                _servicos.value = servicoCardDTOList
            }
        }
    }
}