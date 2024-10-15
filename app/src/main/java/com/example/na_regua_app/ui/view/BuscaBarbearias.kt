import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.ui.components.Botao
import com.example.na_regua_app.ui.components.BottomBarCustom
import com.example.na_regua_app.ui.components.TopBarCustom
import com.example.na_regua_app.ui.theme.Typography
import com.example.na_regua_app.ui.view.CalendarExample

@Composable
fun BuscaBarbearias(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopBarCustom(navController, "Busca", true)
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(14.dp)
            ) {
                item {
                    textoAcimaOpcoesBusca()
                }
                item {
                    opcoesBuscaBarbearia(
                        onServiceClick = {
                            navController.navigate("selecionarServico")
                        },
                        onDateClick = {
                            navController.navigate("selecionarData")
                        },
                        onLocalClick = {
                            navController.navigate("selecionarLocal")
                        }
                    )
                }
            }
        },
        bottomBar = {
            BottomBarCustom(navController)
        }
    )
}

@Composable
fun SelecionarLocalScreen(navController: NavHostController) {
    val localAtual = "Rua Fictícia, 123 - Centro"
    val enderecosVisitados = listOf(
        "Rua Exemplo, 456 - Bairro A",
        "Avenida Teste, 789 - Bairro B",
        "Travessa Simulação, 1011 - Bairro C"
    )

    Scaffold(
        topBar = {
            TopBarCustom(navController, "Selecionar Local", true)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(14.dp)
            ) {
                Text(
                    text = "Localização Atual",
                    style = Typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                OpcaoEndereco(
                    texto = localAtual,
                    onClick = {
                        // Lógica ao selecionar o local atual
                        navController.navigate("buscarBarbearias") // Voltar para a tela anterior, ou fazer outra ação
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Locais Visitados",
                    style = Typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(enderecosVisitados) { endereco ->
                        OpcaoEndereco(
                            texto = endereco,
                            onClick = {
                                // Lógica ao selecionar um local visitado
                                navController.navigate("buscarBarbearias") // Voltar para a tela anterior, ou fazer outra ação
                            }
                        )
                        Spacer(modifier = Modifier.height(15.dp)) // Espaço entre os endereços visitados
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        // Lógica ao clicar no botão de confirmação
                        navController.navigate("buscarBarbearias") // Voltar para a tela anterior, ou fazer outra ação
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    Text(text = "Confirmar")
                }
            }
        },
        bottomBar = {
            BottomBarCustom(navController)
        }
    )
}

@Composable
fun SelecionarDataScreen(navController: NavHostController) {
    var selectedDate by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopBarCustom(navController, "Selecionar Data", true)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(14.dp)
            ) {
                // Exibir o calendário
                CalendarExample { date ->
                    selectedDate = date
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Exibir a data selecionada (se houver)
                selectedDate?.let {
                    Text(
                        text = "Data selecionada: $it",
                        style = Typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                // Botão de confirmação
                Botao(
                    onClick = {navController.navigate("buscarBarbearias")},
                    textButton = "Marcar Horário"
                )
            }
        },
        bottomBar = {
            BottomBarCustom(navController)
        }
    )
}

@Composable
fun SelecionarServicoScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBarCustom(navController, "Selecionar Serviço", true)
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(14.dp)
            ) {
                item {
                    opcoesServicos { service ->
                        // Redirecionar para a tela de listagemBarbearias
                        navController.navigate("listagemBarbearias")
                    }
                }
            }
        },
        bottomBar = {
            BottomBarCustom(navController)
        }
    )
}

@Composable
fun OpcaoEndereco(texto: String, onClick: () -> Unit) {
    // Estilo de cartão para endereços, sem ícones
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = texto,
            style = Typography.titleSmall
        )
    }
}

@Composable
fun opcoesBuscaBarbearia(
    onServiceClick: () -> Unit,
    onDateClick: () -> Unit,
    onLocalClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OptionRow(icon = Icons.Default.DateRange, text = "Selecionar serviço", onClick = onServiceClick)
        OptionRow(icon = Icons.Default.DateRange, text = "Selecionar data", onClick = onDateClick)
        OptionRow(icon = Icons.Default.DateRange, text = "Selecionar local", onClick = onLocalClick)
    }
}

@Composable
fun opcoesServicos(onServiceClick: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        listOf("Corte", "Alisamento", "Pintura", "Barba", "Hidratação").forEach { service ->
            OptionRow(icon = Icons.Default.DateRange, text = service) {
                onServiceClick(service) // Alterado para usar a rota "listagemBarbearias"
            }
        }
    }
}
@Composable
fun OptionRow(icon: ImageVector, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable(onClick = onClick)
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = text)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, style = Typography.titleSmall)
    }
}

@Composable
fun textoAcimaOpcoesBusca(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "a" )
        Text(
            text = "O que você busca ?",
            style = Typography.titleSmall
        )
    }
}


@Preview
@Composable
fun MyAppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController, startDestination = "buscaBarbearias") {
        composable("buscaBarbearias") { BuscaBarbearias(navController) }
        composable("selecionarServico") { SelecionarServicoScreen(navController) }
        composable("selecionarData") { SelecionarDataScreen(navController) }
        composable("selecionarLocal") { SelecionarLocalScreen(navController) }
    }
}