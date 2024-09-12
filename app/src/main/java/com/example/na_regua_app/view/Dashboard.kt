@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.na_regua_app.view

import Espacamento
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.na_regua_app.R
import com.example.na_regua_app.classes.AgendamentoBarbeiro
import com.example.na_regua_app.classes.criarListaAgendamentos
import com.example.na_regua_app.components.BotaoIcon
import com.example.na_regua_app.components.BottomBarCustom
import com.example.na_regua_app.components.DropDownMenu
import com.example.na_regua_app.components.LineChart
import com.example.na_regua_app.components.TopBarCustom
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopBarCustom(navController, "Dashboard", true)
        },
        content = { paddingValues ->
            DashboardContent(paddingValues)
        },
        bottomBar = {
            BottomBarCustom(navController)
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashboardContent(paddingValues: PaddingValues) {
    val tabs = listOf(
        "Agendamentos",
        "Financeiro",
        "Gerenciamento"
    )
    var currentTabIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(paddingValues)
    ) {
        TabLayout(
            tabs = tabs,
            selectedTabIndex = currentTabIndex,
            onTabSelected = { index ->
                currentTabIndex = index
            }
        )
        TabContent(selectedTabIndex = currentTabIndex)
    }
}

@Composable
fun TabLayout(
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.Transparent,
        contentColor = Color.Gray,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                color = ORANGE_SECUNDARY
            )
        },
        divider = { /* Não desenha nada */ },
        modifier = Modifier.border(0.dp, Color.Transparent)
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                modifier = Modifier.fillMaxWidth(),
                text = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = tab,
                            fontSize = 12.sp
                        )
                    }
                },
                selected = index == selectedTabIndex,
                onClick = { onTabSelected(index) },
                selectedContentColor = ORANGE_SECUNDARY,
                unselectedContentColor = Color.Gray,
                interactionSource = remember { MutableInteractionSource() }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TabContent(selectedTabIndex: Int) {
    when (selectedTabIndex) {
        0 -> AgendamentosContent()
        1 -> FinanceiroContent()
        2 -> GerenciamentoContent()
    }
}

@Composable
fun GerenciamentoContent() {
    Text("Gerenciamento")
}



@Composable
fun FinanceiroContent() {

    val valorReceita by remember { mutableDoubleStateOf(230.00) }
    val valorLucro by remember { mutableDoubleStateOf(230.00) }
    val valorDespesa by remember { mutableDoubleStateOf(230.00) }
    val filterValues = listOf(
        "útimos meses" to { selectedItem: String ->
            println("Ação para $selectedItem: Mes")
            // Coloque aqui o código que deve ser executado quando "Confirmado" é selecionado
        },
        "últimos dias" to { selectedItem: String ->
            println("Ação para $selectedItem: Dia")
            // Coloque aqui o código que deve ser executado quando "Cancelar agendamento" é selecionado
        }
    )

    val valoresDash = listOf(
        "Receita" to valorReceita,
        "Lucro" to valorLucro,
        "Despesa" to valorDespesa
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            valoresDash.forEach {
                CardFinancas(it.first, it.second)
            }
        }

        // Valores e labels para o gráfico
        val values = listOf(10f, 20f, 15f, 30f, 25f)
        val labels = listOf("Mai", "Jun", "Jul", "Ago", "Set")

        // Container para o gráfico
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f).background(color = Color.White,shape = RoundedCornerShape(15.dp) ) // Ocupa o espaço restante
                .padding(16.dp).border(2.dp, color = Color.Gray, shape = RoundedCornerShape(15.dp)).fillMaxWidth()
        ) {

            Row(modifier = Modifier.fillMaxWidth().padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Total Clientes")
                DropDownMenu(filterValues, modifier = Modifier.width(200.dp), menuWidth = 200.dp)
            }


            Box(
                modifier = Modifier.padding(10.dp)
            ){
                LineChart(
                    barValues = values,
                    labelValues = labels,
                    modifier = Modifier
                        .width(330.dp)
                        .height(300.dp)
                )
            }

        }

        // Botão na parte inferior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) // Adiciona um pouco de padding para o botão
        ) {
            BotaoIcon(
                onClick = {},
                textButton = "Lançar valor",
                idIcon = R.drawable.lancar_valor
            )
        }
    }
}


@Composable
fun CardFinancas(title: String, valor: Double){
    Column (
        modifier = Modifier.background(BLUE_PRIMARY, shape = RoundedCornerShape(15.dp))
            .padding(20.dp)
            .width(75.dp)
            .height(75.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = title,
        color = Color.White,
        fontSize = 15.sp)

        Espacamento(10.dp)

        Text(text = "R$$valor",
            color = Color.White,
            fontSize = 19.sp)
    }
}

@Composable
fun AgendamentosContent() {
    var qtdAgendamentos by remember { mutableStateOf("3") }
    var agendamentos = criarListaAgendamentos()

    var agPendentes = agendamentos.filter { it.status == "Pendente" }
    var agConfirmados = agendamentos.filter { it.status == "Agendado" }
    var agConcluidos = agendamentos.filter { it.status == "Conluido" }
    var agCancelado = agendamentos.filter { it.status == "Cancelado" }

    Espacamento(20.dp)

    Column {
        SectionAgendamentos(
            actionNames = listOf(
                "Confirmar agendamento" to { selectedItem: String ->
                    println("Ação para $selectedItem: Confirmar")
                    // Coloque aqui o código que deve ser executado quando "Confirmado" é selecionado
                },
                "Cancelar agendamento" to { selectedItem: String ->
                    println("Ação para $selectedItem: Cancelar")
                    // Coloque aqui o código que deve ser executado quando "Cancelar agendamento" é selecionado
                }
            ),
            agendamentos = agPendentes,
            statusAgendamentos = pluralStringResource(
                id = R.plurals.pendentes,
                count = agPendentes.size, agPendentes.size
            )
        )

        Espacamento(20.dp)


        SectionAgendamentos(
            actionNames = listOf(
                "Confirmado" to { selectedItem: String ->
                    println("Ação para $selectedItem: Confirmar")
                    // Coloque aqui o código que deve ser executado quando "Confirmado" é selecionado
                },
                "Cancelar agendamento" to { selectedItem: String ->
                    println("Ação para $selectedItem: Cancelar")
                    // Coloque aqui o código que deve ser executado quando "Cancelar agendamento" é selecionado
                }
            ),
            agendamentos = agConfirmados,
            statusAgendamentos = pluralStringResource(
                id = R.plurals.confirmado,
                count = agConfirmados.size, agConfirmados.size
            )
        )
    }
}


@Composable
fun SectionAgendamentos(actionNames: List<Pair<String, (String) -> Unit>>,
                        agendamentos: List<AgendamentoBarbeiro>,
                        statusAgendamentos: String){

    Column (
        modifier = Modifier.padding(20.dp, top = 0.dp)
    ) {
        Text(text = statusAgendamentos,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium)

        Espacamento(10.dp)

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(agendamentos) {
                CardAgendamento(it, actionNames)
            }

        }
    }

}


@Composable
fun CardAgendamento(agendamentoBarbeiro: AgendamentoBarbeiro, actionsName: List<Pair<String, (String) -> Unit>>) {

        Column (
            modifier = Modifier.width(250.dp)
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(15.dp))
        ){
            Row (
                modifier = Modifier.fillMaxWidth().padding(15.dp, bottom = 0.dp, top = 15.dp)
            ){
                Image(
                    painter = rememberAsyncImagePainter(agendamentoBarbeiro.imgCliente),
                    contentDescription = "Imagem do cliente",
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .border(2.dp, BLUE_PRIMARY, shape = RoundedCornerShape(12.dp))
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Column (
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(8.dp, 0.dp).height(50.dp)
                ){
                    Text(text = agendamentoBarbeiro.nomeCliente,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal)
                    Text(text = formatarDataHoraAg(agendamentoBarbeiro.dataHora),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light)
                }
            }

            DropDownMenu(actionsName, modifier = Modifier.fillMaxWidth(), menuWidth = 250.dp)
        }
}



@SuppressLint("NewApi")
fun formatarDataHoraAg(dataHora: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - H'h'")
    return dataHora.format(formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun DashboardPreview() {
    val navController = rememberNavController()
    Dashboard(navController = navController)
}
