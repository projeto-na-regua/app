package com.example.na_regua_app.viewmodel

import android.util.Log
import androidx.compose.runtime.LongState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.na_regua_app.data.api.FuncionarioService
import com.example.na_regua_app.data.model.Barbearia
import com.example.na_regua_app.data.model.BarbeiroConsulta
import com.example.na_regua_app.data.model.Funcionario
import com.example.na_regua_app.data.model.NovoBarbeiro
import com.example.na_regua_app.data.repository.FuncionarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class FuncionarioViewModel(
    private val funcionarioRepository: FuncionarioRepository
) : ViewModel() {

    private val _funcionarios = MutableStateFlow<List<Funcionario>?>(emptyList())
    val funcionarios: StateFlow<List<Funcionario>?> get() = _funcionarios

    fun obterFuncionarios(idBarbearia: Int, isBarbeiro: Boolean) {
        viewModelScope.launch {
            try {
                val funcionariosData: Response<List<Funcionario>> = if (isBarbeiro) {
                    funcionarioRepository.obterFuncionarios()
                } else {
                    funcionarioRepository.obterFuncionariosCliente(idBarbearia)
                }
                if (funcionariosData.isSuccessful) {
                    val funcionarios = funcionariosData.body()
                    _funcionarios.value = funcionarios
                    Log.d("FuncionarioViewModel", "Dados dos funcionarios: ${funcionariosData.body()}")
                } else {
                    Log.e("FuncionarioViewModel", "Erro na resposta: ${funcionariosData.code()}")
                    Log.e("FuncionarioViewModel", "Erro ao obter funcionarios: ${funcionariosData.errorBody()?.string()}")
                    _funcionarios.value = null
                }
            } catch (e: Exception) {
                Log.e("FuncionarioViewModel", "Erro ao obter funcionarios: ${e.message}")
                _funcionarios.value = null
            }
        }
    }

    fun cadastrarFuncionario(novoBarbeiro: NovoBarbeiro, onResult: (Boolean) -> Unit){
        viewModelScope.launch {
            try {
                val funcionarioData = funcionarioRepository.cadastrarFuncionario(novoBarbeiro)

                if(funcionarioData.isSuccessful){
                    Log.d("FuncionarioViewModel", "Dados do funcionario: ${funcionarioData.body()}")
                    onResult(true)
                } else {
                    Log.e("FuncionarioViewModel", "Erro na resposta: ${funcionarioData.code()}")
                    Log.e("FuncionarioViewModel", "Erro ao cadastrar o funcionario: ${funcionarioData.errorBody()?.string()}")
                    onResult(false)
                }
            } catch (e: Exception){
                Log.e("FuncionarioViewModel", "Erro ao cadastrar o funcionário: ${e.message}")
                onResult(false)
            }
        }
    }


}