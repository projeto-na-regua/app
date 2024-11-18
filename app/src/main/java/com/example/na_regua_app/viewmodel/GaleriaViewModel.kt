package com.example.na_regua_app.viewmodel

import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.na_regua_app.data.model.Funcionario
import com.example.na_regua_app.data.model.GaleriaConsulta
import com.example.na_regua_app.data.repository.GaleriaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.FileNotFoundException
import java.io.InputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GaleriaViewModel (
    private val galeriaRepository: GaleriaRepository
) : ViewModel() {

    private val _imagensGaleria = MutableStateFlow<List<String>?>(emptyList())
    val imagensGaleria: StateFlow<List<String>?> get() = _imagensGaleria

    @RequiresApi(Build.VERSION_CODES.O)
    fun postarImagem(imagemFile: MultipartBody.Part, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                // Cria a descrição da imagem
                val descricao = "Corte do dia " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "!"
                val descricaoBody = descricao.toRequestBody("text/plain".toMediaTypeOrNull())

                // Chama o repositório para postar a imagem
                val response = galeriaRepository.postarImagem(imagemFile, descricaoBody)

                // Checa se a resposta foi bem-sucedida
                if (response.isSuccessful) {
                    onResult(true)
                } else {
                    Log.e("GaleriaViewModel", "Erro: ${response.errorBody()?.string()}")
                    onResult(false)
                }
            } catch (e: Exception) {
                onResult(false)
                Log.e("GaleriaViewModel", "Erro ao enviar a foto para a galeria: ${e.message}")
            }
        }
    }


    fun getImagens() {
        viewModelScope.launch {
            try {
                val response = galeriaRepository.getImages()

                if (response.isSuccessful) {
                    // Aqui estamos assumindo que a resposta é uma lista de objetos JSON
                    val imagens = response.body()?.map { it.linkImagem } ?: emptyList()

                    // Atualizando o valor de _imagensGaleria com a lista de links de imagens
                    _imagensGaleria.value = imagens

                    Log.d("GaleriaViewModel", "Dados das imagens da galeria: $imagens")
                } else {
                    Log.e("GaleriaViewModel", "Erro: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("GaleriaViewModel", "Erro ao enviar a foto para a galeria: ${e.message}")
            }
        }
    }



}

