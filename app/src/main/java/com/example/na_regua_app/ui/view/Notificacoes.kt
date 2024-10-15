package com.example.na_regua_app.ui.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.data.model.Notificacao
import com.example.na_regua_app.data.model.Usuario
import com.example.na_regua_app.data.model.usuarios
import com.example.na_regua_app.ui.components.BottomBarCustom
import com.example.na_regua_app.ui.components.TopBarCustom
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.NEW_NOTIFICATION
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Notificacoes(
    navController: NavController,
    usuario: Usuario
) {
    Scaffold(
        topBar = {
            TopBarCustom(navController, "Notificações", false)
        },
        content = { paddingValues ->
            NotificacoesContent(
                paddingValues = paddingValues
            )
        },
        bottomBar = {
            BottomBarCustom(navController, usuario)
        }
    )
}



@RequiresApi(Build.VERSION_CODES.O)
fun requestNotificacao(): List<Notificacao> {
    return listOf(
        Notificacao(
            id = 1,
            nome = "Agendamento Concluído",
            tipo = "Barbearia",
            dataHora = LocalDateTime.of(2024, 8, 1, 14, 0),
            descricao = "Corte de cabelo concluído às 14h",
            visto = false
        ),
        Notificacao(
            id = 2,
            nome = "João curtiu seu post",
            tipo = "Comunidade",
            dataHora = LocalDateTime.of(2024, 8, 2, 9, 30),
            descricao = "Seu post foi curtido por João",
            visto = false
        ),
        Notificacao(
            id = 3,
            nome = "Maria comentou no seu post",
            tipo = "Comunidade",
            dataHora = LocalDateTime.of(2024, 8, 3, 19, 0),
            descricao = "Maria: 'Ótimo corte!'",
            visto = false
        ),
        Notificacao(
            id = 4,
            nome = "Agendamento Agendado",
            tipo = "Barbearia",
            dataHora = LocalDateTime.of(2024, 8, 4, 16, 0),
            descricao = "Você tem um agendamento às 16h",
            visto = false
        ),
        Notificacao(
            id = 5,
            nome = "Carlos curtiu seu post",
            tipo = "Comunidade",
            dataHora = LocalDateTime.of(2024, 8, 5, 11, 0),
            descricao = "Seu post foi curtido por Carlos",
            visto = false
        ),
        Notificacao(
            id = 6,
            nome = "Ana curtiu seu post",
            tipo = "Comunidade",
            dataHora = LocalDateTime.of(2024, 8, 6, 10, 0),
            descricao = "Seu post foi curtido por Ana",
            visto = false
        ),
        Notificacao(
            id = 7,
            nome = "Lucas comentou no seu post",
            tipo = "Comunidade",
            dataHora = LocalDateTime.of(2024, 8, 7, 15, 30),
            descricao = "Lucas: 'Excelente serviço!'",
            visto = false
        ),
        Notificacao(
            id = 8,
            nome = "Agendamento Concluído",
            tipo = "Barbearia",
            dataHora = LocalDateTime.of(2024, 8, 8, 13, 0),
            descricao = "Corte de barba concluído às 13h",
            visto = true
        ),
        Notificacao(
            id = 9,
            nome = "Paula curtiu seu post",
            tipo = "Comunidade",
            dataHora = LocalDateTime.of(2024, 8, 9, 18, 0),
            descricao = "Seu post foi curtido por Paula",
            visto = true
        ),
    )

}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificacoesContent(paddingValues: PaddingValues) {

    var notificacoes = requestNotificacao()

    val quantidadeGeral = notificacoes.size
    val quantidadeBarbearia = notificacoes.count { it.tipo == "Barbearia" }
    val quantidadeComunidade = notificacoes.count { it.tipo == "Comunidade" }

    val tabs = listOf(
        "Geral" to quantidadeGeral,
        "Comunidade" to quantidadeComunidade,
        "Sua barbearia" to quantidadeBarbearia
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
        TabContent(selectedTabIndex = currentTabIndex, notificacoes)
    }

}

@Composable
fun TabLayout(
    tabs: List<Pair<String, Int>>,
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
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = tab.first,
                            fontSize = 12.sp
                        )

                        if (tab.second > 0) {
                            Box(
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .size(20.dp)
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(ORANGE_SECUNDARY),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = tab.second.toString(),
                                    fontSize = 12.sp,
                                    color = BLUE_PRIMARY,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
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
fun TabContent(selectedTabIndex: Int,
               notificacoes: List<Notificacao>) {

    val notificacoesBarbearia = notificacoes.filter { it.tipo == "Barbearia" }
    val notificacoesComunidade = notificacoes.filter { it.tipo == "Comunidade" }

    when (selectedTabIndex) {
        0 -> GeralContent(notificacoes)
        1 -> ComunidadeContent(notificacoesComunidade)
        2 -> SuaBarbeariaContent(notificacoesBarbearia)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GeralContent(notificacoes: List<Notificacao>) {
    ListContent(notificacoes)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ComunidadeContent(notificacoes: List<Notificacao>) {
    ListContent(notificacoes)
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SuaBarbeariaContent(notificacoes: List<Notificacao>) {
    ListContent(notificacoes)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ListContent(notificacoes: List<Notificacao>) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        if (notificacoes.isEmpty()) {
            Text(
                text = "Nenhuma notificação",
                color = Color.Gray,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.TopStart)
            )
        } else {
            LazyColumn {
                items(notificacoes) { notificacao ->
                    NotificacaoItem(notificacao)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificacaoItem(notificacao: Notificacao) {
    var visto by remember { mutableStateOf(notificacao.visto) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (visto) {
                    Color.Transparent
                } else {
                    NEW_NOTIFICATION
                }
            )
            .border(0.5.dp, Color.Gray.copy(alpha = 0.5f))
            .clickable {
                visto = true
                notificacao.visto = true
            }
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = notificacao.nome,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Text(
                text = formatarDataHora(notificacao.dataHora),
                fontSize = 12.sp,
                color = Color.DarkGray
            )
        }

        Espacamento(15.dp)

        Text(
            modifier = Modifier.padding(bottom = 10.dp, start = 10.dp),
            text = notificacao.descricao,
            fontSize = 14.sp,
            fontWeight = FontWeight.ExtraLight
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun formatarDataHora(dataHora: LocalDateTime): String {
    val formatter = DateTimeFormatter
    .ofPattern("d MMM yyyy 'às' HH:mm", Locale("pt", "BR"))

    return dataHora.format(formatter)
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun NotificacoesPreview() {
    val usuarios = usuarios()
    val navController = rememberNavController()
    Notificacoes(navController = navController, usuarios.first())
}
