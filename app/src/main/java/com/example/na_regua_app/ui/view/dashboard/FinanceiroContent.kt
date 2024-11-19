package com.example.na_regua_app.ui.view.dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.na_regua_app.R
import com.example.na_regua_app.data.model.Dado
import com.example.na_regua_app.data.model.ItemMenuDropDown
import com.example.na_regua_app.ui.components.BotaoIcon
import com.example.na_regua_app.ui.components.DropDownMenu
import com.example.na_regua_app.ui.components.LineChartSpan
import com.example.na_regua_app.ui.components.ModalLancarValor
import com.example.na_regua_app.ui.components.PieChartSpan
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.ui.view.Espacamento
import com.example.na_regua_app.viewmodel.FinancaViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.KoinApplication.Companion.init
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FinanceiroContent(
    financaViewModel: FinancaViewModel = koinViewModel(),
) {
    var isModalLancarValorVisible by remember { mutableStateOf(false) }

    // Coletando dados do ViewModel
    val receita = financaViewModel.dadosReceita.collectAsState().value
    val despesa = financaViewModel.dadosDespesa.collectAsState().value
    val lucro = financaViewModel.lucro.collectAsState().value
    val lucratividade = financaViewModel.lucratividade.collectAsState().value

    // Coletando dados para o gráfico
    val datasGrafico = financaViewModel.datasGrafico.collectAsState().value
    val qtdClientes = financaViewModel.qtdClientes.collectAsState().value

    // Estado para a quantidade de dias selecionados
    var qtdDias by remember { mutableStateOf(7) }

    // Atualiza os dados ao iniciar
    LaunchedEffect(qtdDias) {
        val startDate = LocalDate.now().minusDays(qtdDias.toLong())
        financaViewModel.obterFinancas(qtdDias, startDate, LocalDate.now()) {}
        financaViewModel.obterMetricasGerais(startDate, LocalDate.now(), qtdDias) {}
    }

    val filterValues = listOf(
        ItemMenuDropDown(
            name = "Últimos 7 dias",
            action = { qtdDias = 7 }
        ),
        ItemMenuDropDown(
            name = "Últimos 15 dias",
            action = { qtdDias = 15 }
        ),
        ItemMenuDropDown(
            name = "Últimos 30 dias",
            action = { qtdDias = 30 }
        ),
        ItemMenuDropDown(
            name = "Últimos 3 meses",
            action = { qtdDias = 90 }
        )
    )

    // Lógica para preparar dados para o gráfico
    val lineChartData = if (datasGrafico.size == qtdClientes.size) {
        datasGrafico.mapIndexed { index, data ->
            Dado(qtdClientes[index].toDouble(), data)
        }
    } else {
        emptyList()
    }

    // Prepara o Dado da lucratividade
    val pieChartData = Dado(lucratividade, "Lucro", color = BLUE_PRIMARY)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Card para Receita, Lucro e Despesa
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            listOf(
                "Receita" to receita,
                "Lucro" to lucro,
                "Despesa" to despesa
            ).forEach {
                CardFinancas(it.first, it.second)
            }
        }

        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .height(400.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .background(color = Color.White, shape = RoundedCornerShape(15.dp))
                        .border(2.dp, color = Color.Gray, shape = RoundedCornerShape(15.dp))
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Total Clientes")
                        DropDownMenu(
                            items = filterValues,
                            modifier = Modifier.width(200.dp),
                            menuWidth = 200.dp,
                            tamFont = 15.sp,
                            selectedItemPosition = null,
                            onItemSelected = {}
                        )
                    }

                    Box {
                        LineChartSpan(
                            lineChartData,
                            modifier = Modifier
                                .padding(10.dp)
                                .height(200.dp)
                        )
                    }
                }
            }

            item { Espacamento(10.dp) }

            item {
                Column(
                    modifier = Modifier
                        .background(color = Color.White, shape = RoundedCornerShape(15.dp))
                        .border(2.dp, color = Color.Gray, shape = RoundedCornerShape(15.dp))
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Lucratividade")
                    }
                    Box {
                        PieChartSpan(pieChartData, modifier = Modifier.padding(10.dp).height(200.dp))
                    }
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    BotaoIcon(
                        onClick = {
                            isModalLancarValorVisible = true
                        },
                        textButton = "Lançar valor",
                        idIcon = R.drawable.lancar_valor
                    )
                }
                if (isModalLancarValorVisible) {
                    ModalLancarValor(
                        onDismiss = {
                            isModalLancarValorVisible = false
                        },
                        isModalOpen = true
                    )
                }
            }

        }

    }

}


@Composable
fun CardFinancas(title: String, valor: Any?) {
    Column(
        modifier = Modifier
            .background(BLUE_PRIMARY, shape = RoundedCornerShape(15.dp))
            .padding(10.dp)
            .width(90.dp)
            .height(90.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 14.sp
        )

        Espacamento(10.dp)

        // Formata o valor com 2 casas decimais e substitui '.' por ','
        val valorFormatado = String.format("%.2f", (valor as? Number)?.toDouble() ?: 0.0).replace(".", ",")
        Text(
            text = "R$$valorFormatado",
            color = Color.White,
            fontSize = 15.sp
        )
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun FinanceiroContentPreview() {
    FinanceiroContent()
}
