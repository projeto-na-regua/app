package com.example.na_regua_app.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.na_regua_app.data.model.FinancaCriacao
import com.example.na_regua_app.data.repository.FinancaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class FinancaViewModel(
    private val financaRepository: FinancaRepository
) : ViewModel() {


    private val _dadosReceita = MutableStateFlow<Double?>(0.0)
    val dadosReceita: StateFlow<Double?> get() = _dadosReceita

    // Variáveis para armazenar despesa, lucro e lucratividade
    private val _dadosDespesa = MutableStateFlow(0.0)
    val dadosDespesa: StateFlow<Double> get() = _dadosDespesa

    private val _lucro = MutableStateFlow(0.0)
    val lucro: StateFlow<Double> get() = _lucro

    private val _lucratividade = MutableStateFlow(0.0)
    val lucratividade: StateFlow<Double> get() = _lucratividade

    private val _qtdClientes = MutableStateFlow<List<Long>>(emptyList())
    val qtdClientes: StateFlow<List<Long>> get() = _qtdClientes

    private val _datasGrafico = MutableStateFlow<List<String>>(emptyList())
    val datasGrafico: StateFlow<List<String>> get() = _datasGrafico

    private val _agendamentosProximos = MutableStateFlow(0L)
    val agendamentosProximos: StateFlow<Long> get() = _agendamentosProximos

    private val _agendamentosPendentes = MutableStateFlow(0L)
    val agendamentosPendentes: StateFlow<Long> get() = _agendamentosPendentes

    private val _dadosSaldoHome = MutableStateFlow<Double?>(0.0)
    val dadosSaldoHome: StateFlow<Double?> get() = _dadosSaldoHome

    private val _qtdClientesHome = MutableStateFlow<List<Long>>(emptyList())
    val qtdClientesHome: StateFlow<List<Long>> get() = _qtdClientesHome


    @RequiresApi(Build.VERSION_CODES.O)
    fun obterFinancas(qtdDias: Int, dataInicial: LocalDate, dataFinal: LocalDate, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val financaData = financaRepository.obterFinancas(qtdDias, dataInicial, dataFinal)

                if (financaData.isSuccessful) {
                    financaData.body()?.let { financa ->
                        // Formatar o array `servicos` para exibição
                        val servicosFormatados = financa.servicos.joinToString(prefix = "[", postfix = "]") {
                            it.joinToString(prefix = "[", postfix = "]", separator = ", ")
                        }

                        // Garantindo que a receita não seja null
                        val receitaTotal = financa.receita ?: 0.0 // Se receita for null, define como 0.0

                        // Atualiza a receita
                        financa.receita = receitaTotal // Verifique se `receita` é `var`

                        // Garantindo que as despesas não sejam null
                        val despesas = financa.despesa ?: 0.0 // Se despesa for null, define como 0.0
                        val despesaTotal = despesas // Aqui você pode somar as despesas como fez com as receitas

                        // Calcular o lucro
                        val lucroTotal = receitaTotal - despesaTotal

                        // Atualizar os valores de lucro e lucratividade
                        _lucro.value = if (lucroTotal < 0) 0.0 else lucroTotal

                        // Calcular a lucratividade
                        _lucratividade.value = if (receitaTotal > 0) {
                            (lucroTotal / receitaTotal) * 100
                        } else {
                            0.0 // Se a receita for 0, a lucratividade é 0%
                        }

                        _dadosReceita.value = receitaTotal
                        _dadosDespesa.value = despesaTotal

                        Log.d("FinancaViewModel", "Dados de financa: despesa=${despesaTotal}, lucro=${_lucro.value}, receita=$receitaTotal, lucratividade=${_lucratividade.value}%, servicos=$servicosFormatados")
                    }
                    onResult(true)
                } else {
                    Log.e("FinancaViewModel", "Erro na resposta: ${financaData.code()}")
                    Log.e("FinancaViewModel", "Erro ao obter financas: ${financaData.errorBody()?.string()}")
                    onResult(false)
                }
            } catch (e: Exception) {
                Log.e("FinancaViewModel", "Erro ao obter financa: ${e.message}")
                onResult(false)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun obterFinancasHome(qtdDias: Int, dataInicial: LocalDate, dataFinal: LocalDate, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val financaData = financaRepository.obterFinancas(qtdDias, dataInicial, dataFinal)

                if (financaData.isSuccessful) {
                    financaData.body()?.let { financa ->
                        val receitaTotal =
                            financa.receita ?: 0.0

                        financa.receita = receitaTotal

                        val despesas = financa.despesa ?: 0.0

                        val lucroTotal = receitaTotal - despesas

                        _dadosSaldoHome.value = if (lucroTotal < 0) 0.0 else lucroTotal
                    }
                    onResult(true)
                } else {
                    Log.e("FinancaViewModel", "Erro na resposta: ${financaData.code()}")
                    Log.e("FinancaViewModel", "Erro ao obter financas: ${financaData.errorBody()?.string()}")
                    onResult(false)
                }
            } catch (e: Exception) {
                Log.e("FinancaViewModel", "Erro ao obter financa: ${e.message}")
                onResult(false)
            }
        }
    }



    @RequiresApi(Build.VERSION_CODES.O)
    fun obterMetricasGerais(dateInicial: LocalDate, dateFinal: LocalDate, qtdDiasParaGrafico: Int, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val dashboardData = financaRepository.obterMetricasGerais(dateInicial, dateFinal, qtdDiasParaGrafico)

                if (dashboardData.isSuccessful) {
                    val responseBody = dashboardData.body()
                    if (responseBody != null) {

                        Log.d("FinancaViewModel", "Dados de dashboard: $responseBody")

                        // Supondo que responseBody.datasGrafico e responseBody.valoresGrafico têm o mesmo tamanho
                        val formatter = DateTimeFormatter.ofPattern("dd/MM")

                        // Criar pares de datas e valores
                        val dadosCombinados = responseBody.datasGrafico.mapIndexed { index, dateString ->
                            Pair(LocalDate.parse(dateString), responseBody.valoresGrafico[index])
                        }

                        // Ordenar os pares pela data
                        val dadosOrdenados = dadosCombinados.sortedBy { it.first }

                        // Separar as datas e os valores ordenados
                        _datasGrafico.value = dadosOrdenados.map { it.first.format(formatter) }
                        _qtdClientes.value = dadosOrdenados.map { it.second }
                        _agendamentosProximos.value = responseBody.confirmados ?: 0
                        _agendamentosPendentes.value = responseBody.pendentes ?: 0

                        // Se você precisa trabalhar com as datas como LocalDate
                        val datasComoLocalDate = responseBody.getDatasGraficoAsLocalDate()
                        Log.d("FinancaViewModel", "Datas como LocalDate: $datasComoLocalDate")
                        onResult(true)
                    } else {
                        Log.e("FinancaViewModel", "Corpo da resposta é nulo")
                        onResult(false)
                    }
                } else {
                    Log.e("FinancaViewModel", "Erro na resposta: ${dashboardData.code()}")
                    Log.e("FinancaViewModel", "Erro ao obter métricas gerais: ${dashboardData.errorBody()?.string()}")
                    onResult(false)
                }
            } catch (e: Exception) {
                Log.e("FinancaViewModel", "Erro ao obter métricas gerais: ${e.message}")
                onResult(false)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun obterMetricasGeraisClientes(dateInicial: LocalDate, dateFinal: LocalDate, qtdDiasParaGrafico: Int, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val dashboardData = financaRepository.obterMetricasGerais(dateInicial, dateFinal, qtdDiasParaGrafico)

                if (dashboardData.isSuccessful) {
                    val responseBody = dashboardData.body()
                    if (responseBody != null) {
                        val valoresGrafico = responseBody.valoresGrafico // Isso deve ser uma lista de números (Int ou Double)
                        if (valoresGrafico.isNotEmpty()) {
                            _qtdClientesHome.value += valoresGrafico.sum() // Soma todos os valores e acumula
                        }
                        // Se você precisa trabalhar com as datas como LocalDate
                        val datasComoLocalDate = responseBody.getDatasGraficoAsLocalDate()
                        Log.d("FinancaViewModel", "Datas como LocalDate: $datasComoLocalDate")
                        onResult(true)
                    } else {
                        Log.e("FinancaViewModel", "Corpo da resposta é nulo")
                        onResult(false)
                    }
                } else {
                    Log.e("FinancaViewModel", "Erro na resposta: ${dashboardData.code()}")
                    Log.e("FinancaViewModel", "Erro ao obter métricas gerais: ${dashboardData.errorBody()?.string()}")
                    onResult(false)
                }
            } catch (e: Exception) {
                Log.e("FinancaViewModel", "Erro ao obter métricas gerais: ${e.message}")
                onResult(false)
            }
        }
    }


    fun lancarValor(financaCriacao: FinancaCriacao, onResult: (Boolean) -> Unit){
        viewModelScope.launch {
            try {
                val response = financaRepository.lancarValor(financaCriacao)
                if(response.isSuccessful){
                    Log.e("FinancaViewModel", "Valores lançados com sucesso: $financaCriacao")
                    onResult(true)
                } else {
                    Log.e("FinancaViewModel", "Erro ao lançar valores: ${response.code()}")
                    Log.e("FinancaViewModel", "Erro ao lançar valores: ${response.errorBody()?.string()}")
                 onResult(false)
                }
            } catch (e: Exception) {
                Log.e("FinancaViewModel", "Erro ao lançar valor: ${e.message}")
                onResult(false)
            }
        }
    }



}






