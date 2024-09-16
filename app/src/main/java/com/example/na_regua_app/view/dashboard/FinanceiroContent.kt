package com.example.na_regua_app.view.dashboard

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
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
import com.example.na_regua_app.classes.Dado
import com.example.na_regua_app.classes.ItemMenuDropDown
import com.example.na_regua_app.components.BotaoIcon
import com.example.na_regua_app.components.DropDownMenu
import com.example.na_regua_app.components.LineChartSpan
import com.example.na_regua_app.components.PieChartSpan
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.view.Espacamento


@Composable
fun FinanceiroContent() {
    val valorReceita by remember { mutableDoubleStateOf(230.00) }
    val valorLucro by remember { mutableDoubleStateOf(230.00) }
    val valorDespesa by remember { mutableDoubleStateOf(230.00) }
    var isMes by remember { mutableStateOf(true) }

    val filterValues = listOf(
        ItemMenuDropDown(
            name = "Útimos meses",
            action = { isMes = true }
        ),
        ItemMenuDropDown(
            name = "Útimos dias",
            action = { isMes = false }
        )
    )

    // Valores e labels para o gráfico
    val lineChartDataMeses = remember {
        listOf(
            Dado(10f, "Mai"),
            Dado(20f, "Jun"),
            Dado(15f, "Jul"),
            Dado(30f, "Ago"),
            Dado(25f, "Set")
        )
    }
    val lineChartDataDias = remember {
        listOf(
            Dado(3f, "Qui."),
            Dado(1f, "Sex."),
            Dado(15f, "Sab."),
            Dado(4f, "Dom"),
            Dado(5f, "Seg")
        )
    }

    val pieChartData = remember {
        listOf(
            Dado(80f, "Lucro", color = ORANGE_SECUNDARY),
            Dado(20f, "Despesa", color = Color.Gray)
        )
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            listOf(
                "Receita" to valorReceita,
                "Lucro" to valorLucro,
                "Despesa" to valorDespesa
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
                            tamFont = 15.sp
                        )
                    }

                    Box {
                        LineChartSpan(
                            if (isMes) lineChartDataMeses else lineChartDataDias,
                            modifier = Modifier.padding(10.dp).height(200.dp)
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
                        PieChartSpan(
                            pieChartData,
                            modifier = Modifier.padding(10.dp).height(200.dp)
                        )
                    }
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth().padding(top = 10.dp)
                ) {
                    BotaoIcon(
                        onClick = {},
                        textButton = "Lançar valor",
                        idIcon = R.drawable.lancar_valor
                    )
                }
            }
        }


    }
}

@Composable
fun CardFinancas(title: String, valor: Double){
    Column (
        modifier = Modifier.background(BLUE_PRIMARY, shape = RoundedCornerShape(15.dp))
            .padding(10.dp)
            .width(90.dp)
            .height(90.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = title,
            color = Color.White,
            fontSize = 14.sp)

        Espacamento(10.dp)

        Text(text = "R$$valor",
            color = Color.White,
            fontSize = 18.sp)
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun FinanceiroContentPreview() {
    FinanceiroContent()
}
