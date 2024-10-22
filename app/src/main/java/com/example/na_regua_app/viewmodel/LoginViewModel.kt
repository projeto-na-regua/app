package com.example.na_regua_app.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.na_regua_app.data.model.DadosCadastroBarbearia
import com.example.na_regua_app.data.model.DadosLogin
import com.example.na_regua_app.data.repository.UsuarioRepository
import com.example.na_regua_app.utils.obterUsuarioDtype
import com.example.na_regua_app.utils.salvarToken
import com.example.na_regua_app.utils.salvarUsuarioDtype
import kotlinx.coroutines.launch



class LoginViewModel(
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {
    var email = mutableStateOf("")
    var senha = mutableStateOf("")

    fun atualizarEmail(novoEmail: String) {
        email.value = novoEmail
    }

    fun atualizarSenha(novaSenha: String) {
        senha.value = novaSenha
    }

    fun logar(context: Context, onResult: (Boolean) -> Unit) {

        val dadosLogin = DadosLogin(
            email = email.value,
            senha = senha.value,
        )

        viewModelScope.launch {
            try {
                val responseLogin = usuarioRepository.logar(dadosLogin)
                if (responseLogin.isSuccessful) {
                    responseLogin.body()?.let { usuario ->
                        val responseAdmIsTrue = usuarioRepository.admIsTrue(usuario)
                        var admBody: Any? = null

                        if (responseAdmIsTrue.isSuccessful) {
                            admBody = responseAdmIsTrue.body()
                            println("Resposta de admIsTrue: ${admBody.toString()}")
                        } else {
                            Log.e("LoginViewModel", "admIsTrue falhou com código: ${responseAdmIsTrue.code()}")
                        }
                        salvarToken(context, token = usuario)
                        admBody?.let { salvarUsuarioDtype(context, admBody.toString()) }
                        obterUsuarioDtype(context).collect { userDtype ->
                            println("Valor do userDtype: $userDtype")
                        }
                    }
                } else {
                    Log.e("LoginViewModel", "Login falhou com código: ${responseLogin.code()}")
                }

                // Retorna o resultado da operação de login
                onResult(responseLogin.isSuccessful)

            } catch (e: Exception) {
                onResult(false)
                Log.e("LoginViewModel", "Erro ao logar: ${e.message}")
            }
        }


    }
}




