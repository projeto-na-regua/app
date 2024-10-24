package com.example.na_regua_app.viewmodel
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.na_regua_app.data.model.BarbeariaPesquisa
import com.example.na_regua_app.data.repository.PesquisaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PesquisaViewModel(
    private val repository: PesquisaRepository
) : ViewModel() {

    var servico = mutableStateOf("")
    var data = mutableStateOf("")
    var hora = mutableStateOf("")
    var raio = mutableIntStateOf(0)
    var token = mutableStateOf("")

    fun atualizarServico(novoServico: String) {
        servico.value = novoServico
    }

    fun atualizarData(novaData: String) {
        data.value = novaData
    }

    fun atualizarHora(novaHora: String) {
        hora.value = novaHora
    }

    fun atualizarRaio(novoRaio: Int) {
        raio.value = novoRaio
    }

    fun atualizarToken(novoToken: String){
        token.value = novoToken
    }

    fun listarBarbeariasWToken(context: Context, onResult: (Boolean, List<BarbeariaPesquisa>?) -> Unit) {
        viewModelScope.launch {
            try {
                val endpoint = "pesquisa/client-side?servico=${Uri.encode(servico.value)}&date=${data.value}&time=${Uri.encode(hora.value)}&raio=${raio.intValue}"
                Log.d("API_CALL", "Chamando endpoint: $endpoint com token: ${token.value}")

                val responseListaBarbearias = repository.listarBarbeariasWToken(servico.value, data.value, hora.value, raio.intValue, token.value)
                if (responseListaBarbearias.isSuccessful) {
                    val barbearias = responseListaBarbearias.body()

                    withContext(Dispatchers.Main) {
                        if (barbearias != null && barbearias.isNotEmpty()) {
                            onResult(true, barbearias)
                            Toast.makeText(context, "Listagem de barbearias realizada com sucesso!", Toast.LENGTH_SHORT).show()
                        } else {
                            onResult(true, barbearias)
                            Toast.makeText(context, "Nenhuma barbearia encontrada.", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        onResult(false, null)
                        Toast.makeText(context, "Erro ao listar as barbearias. Código: ${responseListaBarbearias.code()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(false, null)
                    Toast.makeText(context, "Erro ao listar as barbearias: ${e.message}", Toast.LENGTH_SHORT).show()
                }
                Log.e("BarbeariaViewModel", "Erro ao listar barbearias: ${e.message}")
            }
        }
    }


    fun listarBarbeariasPorNome(nomeBarbearia: String, onResult: (Boolean, List<BarbeariaPesquisa>?) -> Unit){
        viewModelScope.launch {
            try {
                val endpoint = "pesquisa/client-side/filtro?nomeBarbearia=$nomeBarbearia"
                Log.d("API_CALL", "Chamando endpoint: $endpoint com token: ${token.value}")

                val responseListaBarbearias = repository.listarBarbeariasPorNome(token.value, nomeBarbearia)
                if (responseListaBarbearias.isSuccessful) {
                    val barbearias = responseListaBarbearias.body()

                    barbearias?.forEach { barbearia ->
                        println("ID: ${barbearia.id}")
                        println("Nome do Negócio: ${barbearia.nomeNegocio}")
                        println("Imagem de Perfil: ${barbearia.imgPerfil}")
                        println("Logradouro: ${barbearia.logradouro}")
                        println("Número: ${barbearia.numero}")
                        println("Média de Avaliação: ${barbearia.mediaAvaliacao}")
                        println("-------------")
                    }
                    onResult(true, barbearias)
                } else {
                    Log.e("BarbeariaViewModel", "Falha ao listar barbearias com código: ${responseListaBarbearias.code()}")
                    onResult(false) // Retornar falha
                }
            } catch (e: Exception) {
                onResult(false) // Retornar falha
                Log.e("BarbeariaViewModel", "Erro ao listar barbearias: ${e.message}")
            }
        }
    }

}

private val _barbearias = MutableLiveData<List<BarbeariaPesquisa>?>()
fun onResult(success: Boolean, barbearias: List<BarbeariaPesquisa>? = null) {
    if (success) {
        if (!barbearias.isNullOrEmpty()) {
            _barbearias.postValue(barbearias) // Atualiza o LiveData
            onResult(true, barbearias)
        }

    }
}





