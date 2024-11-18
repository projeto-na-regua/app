package com.example.na_regua_app.ui.view

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.R
import com.example.na_regua_app.data.model.BarbeariaPesquisa
import com.example.na_regua_app.data.model.Usuario
import com.example.na_regua_app.data.model.usuarios
import com.example.na_regua_app.ui.components.BarraPesquisar
import com.example.na_regua_app.ui.components.BottomBarCustom
import com.example.na_regua_app.ui.components.CardImagemInfoBarbearia
import com.example.na_regua_app.ui.components.TopBarCustom
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.utils.obterToken
import com.example.na_regua_app.viewmodel.AgendamentoViewModel
import com.example.na_regua_app.viewmodel.PesquisaViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@OptIn(DelicateCoroutinesApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeUsuario(
    navController: NavController,
    usuario: Usuario
) {
    var botaoClicado by remember { mutableStateOf(false) }
    var nomeBarbearia by remember { mutableStateOf("") }
    var descricaoBarbearia by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.background(White),
        topBar = {
            TopBarCustom(navController, "Home", true)
        },
        content = { paddingValues ->
            HomeUsuarioContent(
                paddingValues = paddingValues,
                navController = navController,
                context = LocalContext.current
            )
        },
        bottomBar = {
            BottomBarCustom(navController, LocalContext.current)
        }
    )
}

@OptIn(DelicateCoroutinesApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeUsuarioContent(
    paddingValues: PaddingValues, navController: NavController,
    agendamentoViewModel: AgendamentoViewModel = koinViewModel(),
    pesquisaViewModel: PesquisaViewModel = koinViewModel(),
    context: Context
) {

    var agendamentoAgendado = agendamentoViewModel.agendamentoAgendadoHomeUsuario.collectAsState().value
    var agendamentoPendente = agendamentoViewModel.agendamentoPendenteHomeUsuario.collectAsState().value
    val barbearias = remember { mutableStateListOf<BarbeariaPesquisa>() }
    val tokenFlow = obterToken(context).collectAsState(initial = "VAZIO")

    var isLoadingPendentes = agendamentoViewModel.isLoadingPendentes.collectAsState().value

    LaunchedEffect(Unit) {
        agendamentoViewModel.listarAgendamentosPendentes()
        agendamentoViewModel.listarAgendamentosAgendados()
    }

    LaunchedEffect(tokenFlow.value) {
        pesquisaViewModel.atualizarToken(tokenFlow.value)

        pesquisaViewModel.listarBarbeariasPorNome("") { success, barbeariasList: List<BarbeariaPesquisa>? ->
            if (success) {
                Log.d("Listar barbearias", "Listagem de barbearias realizada com sucesso!")
                barbearias.clear()
                barbearias.addAll(barbeariasList ?: emptyList())
                Toast.makeText(context, "Listagem de barbearias realizada com sucesso!", Toast.LENGTH_SHORT).show()
                println("MEU DEUS OLHA A LISTA DE BARBEARIAS: $barbeariasList")
            } else {
                Log.d("Listar barbearias", "Erro ao listar as barbearias.")
                Toast.makeText(context, "Erro ao listar as barbearias. Tente novamente.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    LazyColumn(
        contentPadding = paddingValues,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        item {
            BarraPesquisar({ navController.navigate("buscarBarbearias") }, navController, "")
        }

        item {
            Text(
                text = "Informações para você",
                color = BLUE_PRIMARY,
                fontSize = 20.sp,
                letterSpacing = .5.sp,
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .padding(vertical = 15.dp)
            )
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = BLUE_PRIMARY)
                    .padding(vertical = 15.dp, horizontal = 12.dp)
            ) {
                if(isLoadingPendentes){
                    Text(
                        text = "Carregando...", color = White,
                        fontWeight = FontWeight(350),
                        letterSpacing = 1.sp,
                        fontSize = 16.sp,
                        style = TextStyle(
                            lineHeight = 24.sp
                        )
                    )
                } else {
                    if(agendamentoAgendado != null || agendamentoPendente != null){
                        Text(
                            text = buildAnnotatedString {

                                if(agendamentoAgendado != null){
                                    append("Seu compromisso foi confirmado pela barbearia ")
                                } else {
                                    append("Seu agendamento está aguardando confirmação da barbearia ")
                                }

                                withStyle(style = SpanStyle(color = ORANGE_SECUNDARY, fontWeight = FontWeight.Bold)) {
                                    if(agendamentoAgendado != null){
                                        append(agendamentoAgendado.nomeNegocio)
                                    } else {
                                        append(agendamentoPendente!!.nomeNegocio)
                                    }

                                }

                                append(". \nInformações do Agendamento: ")

                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {

                                    if(agendamentoAgendado != null){
                                        append(agendamentoAgendado.tipoServico)
                                    } else {
                                        append(agendamentoPendente!!.tipoServico)
                                    }
                                }

                                append(" no dia ")

                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {

                                    if(agendamentoAgendado != null){
                                        append(formatarDataHoraHomeUsuario(agendamentoAgendado.dataHora))
                                    } else {
                                        append(formatarDataHoraHomeUsuario(agendamentoPendente!!.dataHora))
                                    }
                                }
                            },
                            color = White,
                            fontWeight = FontWeight(500),
                            letterSpacing = 1.sp,
                            fontSize = 16.sp,
                            style = TextStyle(
                                lineHeight = 24.sp
                            )
                        )
                    } else {
                        Text(
                            text = "Você não possui agendamentos pendentes ou confirmados. Conheça uma nova barbearia hoje!",
                            color = White,
                            fontWeight = FontWeight(350),
                            letterSpacing = 1.sp,
                            fontSize = 16.sp,
                            style = TextStyle(
                                lineHeight = 24.sp
                            )
                        )
                    }
                }
            }
        }

        item {

            Text(
                text = "Barbearias para você conhecer",
                color = BLUE_PRIMARY,
                fontSize = 20.sp,
                letterSpacing = .5.sp,
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .padding(top = 25.dp, bottom = 10.dp)
            )
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth(.95f)
            ) {
                val isBarbeiro = false
                items(barbearias.take(5)) { barbearia ->
                    CardImagemInfoBarbearia(
                            onClick = {
                                navController.navigate("perfilBarbearia/$isBarbeiro/${barbearia.id}")
                            },
                            nomeBarbearia = barbearia.nomeNegocio,
                            imagem = barbearia.imgPerfil
                        )
                    Spacer(modifier = Modifier.width(15.dp))
                }
            }
        }




        item {
            Text(
                text = "Pode te interessar",
                color = BLUE_PRIMARY,
                fontSize = 20.sp,
                letterSpacing = .5.sp,
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .padding(top = 40.dp, bottom = 15.dp)
            )
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(150.dp)
                    .clip(RoundedCornerShape(15.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.capa_perfil_barbeiro),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color.Black.copy(alpha = 0.6f))
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .fillMaxHeight()
                        .fillMaxWidth(.9f)
                ) {
                    Text(
                        text = "Possui uma barbearia?",
                        color = White,
                        fontWeight = FontWeight(600),
                        letterSpacing = 1.sp,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    Text(
                        text = "Faça o cadastro dela e tenha uma infinidade de funções para a melhor gestão.",
                        color = White,
                        fontSize = 14.sp,
                        letterSpacing = .5.sp,
                        lineHeight = 24.sp,
                        modifier = Modifier.fillMaxWidth(.8f),
                        fontWeight = FontWeight(300)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clickable {
                                navController.navigate("cadastroBarbearia")
                            }
                            .size(40.dp)
                            .clip(RoundedCornerShape(7.dp))
                            .background(color = BLUE_PRIMARY)
                            .padding(5.dp)
                            .align(Alignment.BottomEnd),
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icone_setinha_svg),
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeUsuarioPreview() {
    val navController = rememberNavController()
    val usuarios = usuarios()
    HomeUsuario(navController = navController, usuarios.first())
}
