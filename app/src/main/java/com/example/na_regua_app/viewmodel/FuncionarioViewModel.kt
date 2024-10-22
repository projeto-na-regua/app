package com.example.na_regua_app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.na_regua_app.data.api.FuncionarioService
import com.example.na_regua_app.data.model.Funcionario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FuncionarioViewModel(
    private val funcionarioService: FuncionarioService
) : ViewModel() {

    private val _funcionarios = MutableStateFlow<List<Funcionario>?>(emptyList())
    val funcionarios: StateFlow<List<Funcionario>?> get() = _funcionarios

    init {
        obterFuncionarios()
    }

    private fun obterFuncionarios() {
        viewModelScope.launch {
            try {
                val response = funcionarioService.obterFuncionarios()
                if (response.isSuccessful) {
                    val funcionarios = response.body()
                    _funcionarios.value = funcionarios
                    Log.d("FuncionarioViewModel", "Dados dos funcionarios: $response")
                } else {
                    Log.e("FuncionarioViewModel", "Erro na resposta: ${response.code()}")
                    Log.e("FuncionarioViewModel", "Erro ao obter funcionarios: ${response.errorBody()?.string()}")
                    _funcionarios.value = null
                }
            } catch (e: Exception) {
                Log.e("FuncionarioViewModel", "Erro ao obter funcionarios: ${e.message}")
                _funcionarios.value = null
            }
        }
    }

}