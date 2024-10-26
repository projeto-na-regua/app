package com.example.na_regua_app.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import androidx.lifecycle.viewModelScope
import com.example.na_regua_app.data.model.DadosCadastroBarbearia
import com.example.na_regua_app.data.model.DadosLogin
import com.example.na_regua_app.data.model.UserDType
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
                        // Verificar se o usuário tem acesso de administrador
                        val responseAdmIsTrue = usuarioRepository.admIsTrue(usuario)
                        var admBody: Any? = null

                        if (responseAdmIsTrue.isSuccessful) {
                            admBody = responseAdmIsTrue.body()  // Captura o retorno do admIsTrue
                            println("Resposta de admIsTrue: ${admBody.toString()}")
                        } else {
                            Log.e("LoginViewModel", "admIsTrue falhou com código: ${responseAdmIsTrue.code()}")
                        }

                        salvarToken(context, token = usuario)
                        admBody?.let {
                            val userDtype = responseAdmIsTrue.body()
                                ?.let { it1 -> UserDType(nome = it1.nome, dtype = it1.dtype, adm = it1.adm) }
                            if (userDtype != null) {
                                salvarUsuarioDtype(context, userDtype)
                            }
                        }

                        viewModelScope.launch {
                            obterUsuarioDtype(context).collect { userDtype ->
                                userDtype?.let {
                                    println("Nome do usuário: ${it.nome}")
                                    println("DType do usuário: ${it.dtype}")
                                    println("isAdm: ${it.adm}")
                                } ?: run {
                                    println("Usuário não encontrado")
                                }
                            }
                        }
                    }
                } else {
                    Log.e("LoginViewModel", "Login falhou com código: ${responseLogin.code()}")
                }
                onResult(responseLogin.isSuccessful)
            } catch (e: Exception) {
                onResult(false)
                Log.e("LoginViewModel", "Erro ao logar: ${e.message}")
            }
        }
    }
}




