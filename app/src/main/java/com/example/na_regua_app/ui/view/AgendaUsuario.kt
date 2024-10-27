package com.example.na_regua_app.ui.view

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.na_regua_app.data.model.AgendamentoConsulta
import com.example.na_regua_app.ui.components.BottomBarCustom
import com.example.na_regua_app.ui.components.ModalHistorico
import com.example.na_regua_app.ui.components.TopBarCustom
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.utils.obterUsuarioDtype
import com.example.na_regua_app.viewmodel.AgendamentoViewModel
import org.koin.compose.viewmodel.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AgendaUsuario(
    navController: NavController,
    agendamentoViewModel: AgendamentoViewModel = koinViewModel(),
) {

    val agendamentosPendentes by agendamentoViewModel.agendamentosPendentes.collectAsState()
    val agendamentosAgendados by agendamentoViewModel.agendamentosAgendados.collectAsState()
    val listaHistorico by agendamentoViewModel.listaHistorico.collectAsState()

    val context = LocalContext.current

    val userDtype by obterUsuarioDtype(context).collectAsState(initial = null)
    val isBarbeiro = userDtype?.dtype == "Barbeiro"


    val agendamentos by remember {
        derivedStateOf { agendamentosPendentes + agendamentosAgendados }
    }

    LaunchedEffect(Unit) {
        agendamentoViewModel.listarAgendamentosPendentes()
        agendamentoViewModel.listarAgendamentosAgendados()
        agendamentoViewModel.listarHistoricoCliente()
    }

    Scaffold(
        modifier = Modifier.background(Color.White),
        topBar = {
            TopBarCustom(navController, "Agendamentos", true)
        },
        content = { paddingValues ->
            AgendaUsuarioContent(
                paddingValues = paddingValues,
                navController = navController,
                agendamentos,
                listaHistorico,
                isBarbeiro,
                agendamentoViewModel
            )
        },
        bottomBar = {
            BottomBarCustom(navController, context)
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AgendaUsuarioContent(
    paddingValues: PaddingValues,
    navController: NavController,
    agendamentos: List<AgendamentoConsulta>,
    listaHistorico: List<AgendamentoConsulta>,
    isBarbeiro: Boolean,
    agendamentoViewModel: AgendamentoViewModel
) {
    var showModal by remember { mutableStateOf(false) }
    var selectedAgendamento by remember { mutableStateOf<AgendamentoConsulta?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 90.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Seus agendamentos",
            fontSize = 18.sp,
            color = BLUE_PRIMARY,
            fontWeight = FontWeight(600),
            letterSpacing = .5.sp,
            modifier = Modifier.fillMaxWidth(.9f)
        )

        Spacer(modifier = Modifier.height(8.dp)) // Espaçamento menor entre os títulos

        // Se for barbeiro, exibe agendamentos pendentes e agendados
        if (isBarbeiro) {
            // Exibir Agendamentos Pendentes
            Text(
                text = "Agendamentos Pendentes",
                fontSize = 16.sp,
                color = BLUE_PRIMARY,
                fontWeight = FontWeight(600),
                modifier = Modifier.padding(vertical = 10.dp)
            )
            if (agendamentos.filter { it.status == "Pendente" }.isEmpty()) {
                Text(
                    text = "Não há agendamentos pendentes.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            } else {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(.9f),
                    contentPadding = PaddingValues(vertical = 10.dp)
                ) {
                    items(agendamentos.filter { it.status == "Pendente" }) { agendamento ->
                        BoxAgendamentos(agendamento, isBarbeiro, agendamentoViewModel, LocalContext.current)
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                }
            }

            // Exibir Agendamentos Agendados
            Text(
                text = "Agendamentos Agendados",
                fontSize = 16.sp,
                color = BLUE_PRIMARY,
                fontWeight = FontWeight(600),
                modifier = Modifier.padding(vertical = 10.dp)
            )
            if (agendamentos.filter { it.status == "Agendado" }.isEmpty()) {
                Text(
                    text = "Não há agendamentos confirmados.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            } else {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(.9f),
                    contentPadding = PaddingValues(vertical = 10.dp)
                ) {
                    items(agendamentos.filter { it.status == "Agendado" }) { agendamento ->
                        BoxAgendamentos(agendamento, isBarbeiro, agendamentoViewModel, LocalContext.current)
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                }
            }
        } else {
            // Se não for barbeiro, ainda exibe agendamentos pendentes e agendados
            Text(
                text = "Agendamentos Pendentes",
                fontSize = 16.sp,
                color = BLUE_PRIMARY,
                fontWeight = FontWeight(600),
                modifier = Modifier.padding(vertical = 10.dp)
            )
            if (agendamentos.filter { it.status == "Pendente" }.isEmpty()) {
                Text(
                    text = "Não há agendamentos pendentes.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            } else {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(.9f),
                    contentPadding = PaddingValues(vertical = 10.dp)
                ) {
                    items(agendamentos.filter { it.status == "Pendente" }) { agendamento ->
                        BoxAgendamentos(agendamento, isBarbeiro, agendamentoViewModel, LocalContext.current)
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                }
            }

            // Exibir Agendamentos Agendados
            Text(
                text = "Agendamentos Agendados",
                fontSize = 16.sp,
                color = BLUE_PRIMARY,
                fontWeight = FontWeight(600),
                modifier = Modifier.padding(vertical = 10.dp)
            )
            if (agendamentos.filter { it.status == "Agendado" }.isEmpty()) {
                Text(
                    text = "Não há agendamentos confirmados.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            } else {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(.9f),
                    contentPadding = PaddingValues(vertical = 10.dp)
                ) {
                    items(agendamentos.filter { it.status == "Agendado" }) { agendamento ->
                        BoxAgendamentos(agendamento, isBarbeiro, agendamentoViewModel, LocalContext.current)
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                }
            }

            // Exibe a seção de histórico
            LazyRow(
                modifier = Modifier.fillMaxWidth(1f)
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .width(200.dp)
                            .align(Alignment.Start)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Histórico",
                                fontSize = 18.sp,
                                color = BLUE_PRIMARY,
                                fontWeight = FontWeight(600),
                                letterSpacing = .5.sp,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                }
            }

            LazyRow(
                modifier = Modifier.fillMaxWidth(.95f)
            ) {
                items(listaHistorico) { agendamento ->
                    BoxHistorico(agendamento = agendamento) {
                        selectedAgendamento = agendamento
                        showModal = true
                    }
                }
            }
        }
    }

    if (showModal && selectedAgendamento != null) {
        ModalHistorico(agendamento = selectedAgendamento!!) {
            showModal = false
            selectedAgendamento = null
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BoxAgendamentos(
    agendamento: AgendamentoConsulta,
    isBarbeiro: Boolean,
    agendamentoViewModel: AgendamentoViewModel,
    context: Context
) {
    val dataHoraFormatada = com.example.na_regua_app.utils.formatarDataHora(agendamento.dataHora.toString())

    // Separa a data e a hora
    val (data, hora) = dataHoraFormatada.split(" ")

    Box(
        modifier = Modifier
            .border(1.dp, BLUE_PRIMARY, RoundedCornerShape(12.dp))
            .padding(horizontal = 4.dp, vertical = 2.dp)
            .width(250.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.Transparent)
                    .border(2.dp, color = Color(0xFF082031), RoundedCornerShape(12.dp))
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = agendamento.imgPerfilBarbearia),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                if (!isBarbeiro) {
                    Text(
                        text = agendamento.nomeNegocio,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        color = BLUE_PRIMARY
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(.75f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = data,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = BLUE_PRIMARY
                    )
                    Text("-")
                    Text(
                        text = hora,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = BLUE_PRIMARY
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                if (isBarbeiro) {
                    TextButton(
                        onClick = {
                            val novoStatus = if (agendamento.status == "Pendente") "Agendado" else "Concluido"
                            agendamentoViewModel.updateStatusAgendamento(agendamento.id, novoStatus) { success ->
                                if (success) {
                                    val mensagem = if (novoStatus == "Agendado") {
                                        "Agendamento confirmado!"
                                    } else {
                                        "Agendamento concluído!"
                                    }
                                    Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(context, "Algo deu errado, tente novamente!", Toast.LENGTH_SHORT).show()
                                }
                            }
                        },
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = if (agendamento.status == "Pendente") "Confirmar agendamento" else "Concluir agendamento",
                            color = if (agendamento.status == "Pendente") ORANGE_SECUNDARY else Color.Green,
                            fontSize = 12.sp
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Image(
                            painter = painterResource(id = com.example.na_regua_app.R.drawable.icone_setinha_baixo),
                            contentDescription = "",
                            modifier = Modifier.size(12.dp)
                        )
                    }
                } else {
                    Text(
                        text = when (agendamento.status) {
                            "Pendente" -> "Pendente"
                            "Agendado" -> "Agendado"
                            else -> "Indefinido"
                        },
                        color = when (agendamento.status) {
                            "Pendente" -> ORANGE_SECUNDARY
                            "Agendado" -> Color.Green
                            else -> Color.Gray
                        },
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}



@Composable
fun BoxHistorico(agendamento: AgendamentoConsulta, onclick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(top = 15.dp)
                .padding(horizontal = 15.dp)
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.Transparent)
                .border(1.dp, color = Color(0xFF9E9E9E), CircleShape)
                .clickable { onclick() } // Chama o onclick para abrir o modal
        ) {
            Image(
                painter = rememberAsyncImagePainter(agendamento.imgPerfilBarbearia),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            text = agendamento.nomeNegocio,
            color = BLUE_PRIMARY,
            fontSize = 15.sp,
            letterSpacing = 1.sp,
            fontWeight = FontWeight(500),
            modifier = Modifier
                .padding(top = 10.dp)
                .widthIn(max = 80.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}
