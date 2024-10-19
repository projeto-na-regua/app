package com.example.na_regua_app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.na_regua_app.data.model.Barbearia
import com.example.na_regua_app.data.repository.BarbeariaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PerfilBarbeariaViewModel (
    private val barbeariaRepository: BarbeariaRepository
) : ViewModel() {

    private val _barbearia = MutableStateFlow<Barbearia?>(null)
    val barbearia: StateFlow<Barbearia?> get() = _barbearia

    init {
        obterBarbearia()
    }

    private fun obterBarbearia() {
        viewModelScope.launch {
            try {
                val barbeariaData = barbeariaRepository.obterBarbearia()
                if (barbeariaData.isSuccessful) {
                    _barbearia.value = barbeariaData.body()
                    Log.d("PerfilBarbeariaViewModel", "Dados da barbearia: $barbeariaData")
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
}