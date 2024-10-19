package com.example.na_regua_app.ui.view

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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.R
import com.example.na_regua_app.data.model.Barbearia
import com.example.na_regua_app.data.model.Funcionario
import com.example.na_regua_app.data.model.ServicoCardDTO
import com.example.na_regua_app.ui.components.BotaoAjustavel
import com.example.na_regua_app.ui.components.BottomBarCustom
import com.example.na_regua_app.ui.components.SelecaoFuncionarios
import com.example.na_regua_app.ui.components.ServiceList
import com.example.na_regua_app.ui.components.TopBarCustom
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.ui.theme.Typography
import com.example.na_regua_app.viewmodel.PerfilBarbeariaViewModel
import com.example.na_regua_app.viewmodel.ServicoViewModel
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilBarbearia(
    navController: NavController,
    perfilBarbeariaViewModel: PerfilBarbeariaViewModel = koinViewModel(),
    servicoViewModel: ServicoViewModel = koinViewModel()

) {
    val barbearia by perfilBarbeariaViewModel.barbearia.collectAsState()
    val servicos by servicoViewModel.servicos.collectAsState()

    LaunchedEffect(Unit) {
        servicoViewModel.obterServicosPorStatus()
        println("Servicos carregados: ${servicos.size}")
    }

    Scaffold(
        topBar = {
            TopBarCustom(navController, "Perfil",  true, true)
        },
        content = { paddingValues ->
            PerfilBarbeariaContent(
                paddingValues = paddingValues,
                navController = navController,
                barbearia = barbearia,
                servicos = servicos
            )
        },
        bottomBar = {
            BottomBarCustom(navController)
        }
    )
}


@Composable
fun PerfilBarbeariaContent(paddingValues: PaddingValues, navController: NavController, barbearia: Barbearia?, servicos: List<ServicoCardDTO>) {

    if (barbearia != null) {
        var nomeBarbearia by remember { mutableStateOf(barbearia.nomeNegocio) }
        var localizacao by remember { mutableStateOf(barbearia.logradouro + ", " + barbearia.numero + " - " + barbearia.cidade) }
        var descricao by remember { mutableStateOf(barbearia.descricao) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(White)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .drawBehind {
                            val strokeWidth = 10.dp.toPx()
                            val y = size.height - strokeWidth / 2
                            drawLine(
                                color = ORANGE_SECUNDARY,
                                start = Offset(0f, y),
                                end = Offset(size.width, y),
                                strokeWidth = strokeWidth
                            )
                        }
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.capa_naregua),
                        contentDescription = "Foto de capa do barbeiro",
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .height(120.dp)
                            .border(1.dp, color = BLUE_PRIMARY),
                        contentScale = ContentScale.Crop
                    )
                }


                Box(
                    modifier = Modifier
                        .offset(y = 90.dp)
                        .padding(start = 12.dp)
                ) {

                    Row(modifier = Modifier.fillMaxWidth()) {

                        Box(
                            modifier = Modifier
                                .size(90.dp)
                                .clip(RoundedCornerShape(50))
                                .border(
                                    2.dp,
                                    color = ORANGE_SECUNDARY,
                                    shape = RoundedCornerShape(50)
                                )
                                .background(Color.White)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.perfil_barbearia),
                                contentDescription = "Foto de perfil",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(50))
                                    .background(Color.White),
                                contentScale = ContentScale.Crop
                            )
                        }

                        Spacer(modifier = Modifier.weight(0.6f))

                        BotaoAjustavel(modifier = Modifier
                            .weight(0.8f)
                            .padding(end = 12.dp)
                            .align(Alignment.Bottom),
                            onClick = { /*TODO*/ },
                            textButton = "Editar perfil",
                            imagePainter = painterResource(R.drawable.edit_icon)
                        )
                    }
                }
            }

            Espacamento(70.dp)

            Box(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .align(Alignment.Start)
            )
            {
                Column {
                    Text(
                        text = nomeBarbearia,
                        color = BLUE_PRIMARY,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        fontSize = 12.sp,
                    )
                    Espacamento(espaco = 8.dp)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.location_icon),
                            contentDescription = "Ícone Localização",
                            modifier = Modifier.padding(end = 4.dp)
                        )

                        Text(
                            text = localizacao,
                            style = Typography.labelSmall,
                            modifier = Modifier.padding(end = 8.dp)
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.start_icon),
                            contentDescription = "Ícone Estrela",
                            modifier = Modifier.padding(end = 4.dp),
                            tint = ORANGE_SECUNDARY
                        )

                        Text(
                            text = "4,5",
                            style = Typography.labelSmall
                        )
                    }
                }
            }

            Espacamento(espaco = 8.dp)

            Box(modifier = Modifier.weight(1f)) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    item {
                        Text(text = "Descrição", style = Typography.titleMedium)
                        Espacamento(espaco = 8.dp)
                    }
                    item {
                        descricao?.let { Text(text = it, style = Typography.labelSmall) }
                        Espacamento(espaco = 8.dp)
                    }
                    item {
                        BoxServicosPerfil(servicos)
                        Espacamento(espaco = 8.dp)
                    }
                    item {
                        BoxFuncionarios(navController)
                        Espacamento(espaco = 8.dp)
                    }
                }
            }

            // Fixed buttons at the bottom
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(9.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 9.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val userName by remember { mutableStateOf("Dom Bigode") }
                    val profilePic by remember { mutableIntStateOf(R.drawable.barbeiro1) }
                    val profilePicString = profilePic.toString()
                    val origin by remember { mutableStateOf("pefilBarbearia") }

                    BotaoAjustavel(
                        modifier = Modifier.weight(1.5f),
                        onClick = { navController.navigate("chat/$userName/$profilePicString/$origin") },
                        textButton = "Enviar mensagem",
                        imagePainter = painterResource(R.drawable.send_icon)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    BotaoAjustavel(
                        modifier = Modifier.weight(1.5f),
                        onClick = { navController.navigate("agendamento") },
                        textButton = "Marcar horário"
                    )
                }
            }
        }
    } else {
        // Mostre uma mensagem de carregamento ou erro...
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Carregando barbearia...", color = Color.Gray)
        }
    }
}



    @Preview(showBackground = true)
    @Composable
    fun PerfilBarbeariaPreview() {
        val navController = rememberNavController()
        PerfilBarbearia(navController = navController)
    }


    @Composable
    fun BoxServicosPerfil(servicos: List<ServicoCardDTO>) {

        var verMais by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Serviços",
                    style = Typography.titleMedium
                )

                Espacamento(espaco = 8.dp)

                val servicosExibidos = if (verMais) servicos else servicos.take(2)
                ServiceList(services = servicosExibidos, isSelectable = false)

                if (servicos.size > 2) {
                    TextButton(
                        onClick = { verMais = !verMais },
                        modifier = Modifier
                            .padding(0.dp)
                            .align(Alignment.CenterHorizontally),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = if (verMais) "Visualizar menos" else "Visualizar mais",
                            color = ORANGE_SECUNDARY,
                            modifier = Modifier.padding(0.dp)
                        )
                    }
                }
            }
        }
    }


@Composable
fun BoxFuncionarios(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Funcionários",
            style = Typography.titleMedium,
        )

        val funcionarios = listOf(
            Funcionario(1, nome = "Diego", imgPerfil = R.drawable.foto_perfil, dtype =  "Administrador", email = "barbeiro@gmail.com"),
            Funcionario(2, nome = "Beatriz V.", imgPerfil = R.drawable.barbeira2,  dtype ="Barbeira", email = "barbeiro@gmail.com"),
            Funcionario(3, nome = "Roberto M.", imgPerfil = R.drawable.barbeiro1,  dtype ="Barbeiro", email = "barbeiro@gmail.com")
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(3) { index ->
                Box(modifier = Modifier.clickable {
                    navController.navigate("perfilUsuario")
                }) {
                    SelecaoFuncionarios(
                        funcionario = funcionarios[index],
                        isSelectable = false,
                        exibirInformacoes = true
                    )
                }
            }
        }
    }
}
