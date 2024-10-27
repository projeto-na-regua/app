package com.example.na_regua_app.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
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
import com.example.na_regua_app.viewmodel.FuncionarioViewModel
import com.example.na_regua_app.viewmodel.PerfilBarbeariaViewModel
import com.example.na_regua_app.viewmodel.ServicoViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilBarbearia(
    navController: NavController,
    isBarbeiro: Boolean,
    idBarbearia: Int,
    perfilBarbeariaViewModel: PerfilBarbeariaViewModel = koinViewModel(),
    servicoViewModel: ServicoViewModel = koinViewModel(),
    funcionarioViewModel: FuncionarioViewModel = koinViewModel()
) {

    LaunchedEffect(Unit) {
        perfilBarbeariaViewModel.obterBarbearia(isBarbeiro, idBarbearia)
        servicoViewModel.obterServicosPorStatus(status = "active", idBarbearia = idBarbearia, isBarbeiro = isBarbeiro)
        funcionarioViewModel.obterFuncionarios(idBarbearia = idBarbearia, isBarbeiro = isBarbeiro)
    }

    val barbearia by perfilBarbeariaViewModel.barbearia.collectAsState()
    val servicos by servicoViewModel.servicos.collectAsState()
    val funcionarios by funcionarioViewModel.funcionarios.collectAsState()

    Scaffold(
        topBar = { TopBarCustom(navController, "Perfil", true, true) },
        content = { paddingValues ->
            if (barbearia != null) {
                var nomeBarbearia by remember { mutableStateOf(barbearia!!.nomeNegocio) }
                var localizacao by remember { mutableStateOf("${barbearia!!.logradouro}, ${barbearia!!.numero} - ${barbearia!!.cidade}") }
                var descricao by remember { mutableStateOf(barbearia!!.descricao) }
                var imgBanner by remember { mutableStateOf(barbearia!!.imgBanner) }
                var imgPerfil by remember { mutableStateOf(barbearia!!.imgPerfil) }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(Color.White)
                ) {
                    // Cabeçalho
                    item {
                        HeaderSection(navController, nomeBarbearia, localizacao, descricao, imgBanner, imgPerfil, isBarbeiro)
                    }

                    // Lista de serviços
                    item {
                        BoxServicosPerfil(servicos)
                    }

                    // Lista de funcionários
                    item {
                        LazyRow {
                            item{
                                BoxFuncionarios(navController, funcionarios, isBarbeiro)
                            }
                        }
                    }

                    // Botões
                    item {
                        FooterButtons(navController, idBarbearia)
                    }
                }

            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Carregando barbearia...", color = Color.Gray)
                }
            }
        },
        bottomBar = { BottomBarCustom(navController, LocalContext.current) }
    )
}

@Composable
fun HeaderSection(navController: NavController, nomeBarbearia: String, localizacao: String, descricao: String?, imgBanner: String?, imgPerfil: String?, isBarbeiro: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(White)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = imgBanner),
            contentDescription = "Foto de capa do barbeiro",
            modifier = Modifier
                .fillMaxSize()
                .border(1.dp, color = BLUE_PRIMARY),
            contentScale = ContentScale.Crop
        )

        // Foto do perfil
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 12.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = imgPerfil),
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(50))
                    .border(2.dp, color = ORANGE_SECUNDARY, shape = RoundedCornerShape(50)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.weight(0.6f))

            if(isBarbeiro){
                BotaoAjustavel(
                    modifier = Modifier
                        .weight(0.8f)
                        .padding(end = 12.dp),
                    onClick = { navController.navigate("settingsbusiness") },
                    textButton = "Editar perfil",
                    imagePainter = painterResource(R.drawable.edit_icon),
                    shape = RoundedCornerShape(12.dp),
                    fontSize = 10
                )
            }

        }
    }

    Column(modifier = Modifier.padding(12.dp)) {
        Text(
            text = nomeBarbearia,
            color = BLUE_PRIMARY,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.location_icon),
                contentDescription = "Ícone Localização",
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(text = localizacao, style = Typography.labelSmall)
            Icon(
                painter = painterResource(id = R.drawable.start_icon),
                contentDescription = "Ícone Estrela",
                modifier = Modifier.padding(end = 4.dp),
                tint = ORANGE_SECUNDARY
            )
            Text(text = "4,5", style = Typography.labelSmall)
        }

        Text(text = "Descrição", style = Typography.titleMedium)
        descricao?.let { Text(text = it, style = Typography.labelSmall) }
    }
}

@Composable
fun FooterButtons(navController: NavController, idBarbearia: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(9.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val userName by remember { mutableStateOf("Dom Bigode") }
        val profilePic by remember { mutableStateOf(R.drawable.barbeiro1) }
        val profilePicString = profilePic.toString()

        BotaoAjustavel(
            modifier = Modifier.weight(1.5f),
            onClick = { navController.navigate("chat/$userName/$profilePicString/perfilBarbearia") },
            textButton = "Enviar mensagem",
            imagePainter = painterResource(R.drawable.send_icon),
            shape = RoundedCornerShape(12.dp),
            fontSize = 10
        )

        BotaoAjustavel(
            modifier = Modifier.weight(1.5f),
            onClick = { navController.navigate("agendamento/$idBarbearia/${false}") },
            textButton = "Marcar horário",
            shape = RoundedCornerShape(12.dp),
            fontSize = 10
        )
    }
}

@Composable
fun BoxServicosPerfil(servicos: List<ServicoCardDTO>) {
    var verMais by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Serviços", style = Typography.titleMedium)

        if (servicos.isEmpty()) {
            Text(
                text = "Nenhum serviço cadastrado",
                style = Typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
        } else {
            val servicosExibidos = if (verMais) servicos else servicos.take(2)
            ServiceList(services = servicosExibidos, isSelectable = false)

            if (servicos.size > 2) {
                TextButton(
                    onClick = { verMais = !verMais },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = if (verMais) "Visualizar menos" else "Visualizar mais", color = ORANGE_SECUNDARY)
                }
            }
        }
    }
}

@Composable
fun BoxFuncionarios(navController: NavController, funcionarios: List<Funcionario>?, isBarbeiro: Boolean) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Funcionários", style = Typography.titleMedium)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            funcionarios?.forEach { funcionario ->
                Box(
                    modifier = Modifier
                        .then(
                            if (isBarbeiro) Modifier.clickable {
                                navController.navigate("perfilUsuario")
                            } else Modifier
                        )
                ) {
                    SelecaoFuncionarios(funcionario = funcionario, isSelectable = false, exibirInformacoes = true)
                }
            }
        }
    }
}
