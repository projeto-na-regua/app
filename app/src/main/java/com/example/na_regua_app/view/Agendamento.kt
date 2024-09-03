package com.example.na_regua_app.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.na_regua_app.components.BottomBarCustom
import com.example.na_regua_app.components.TopBarCustom
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Agendamento(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopBarCustom(navController, "Perfil", true)
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(14.dp)
            ) {
                item {
                    BoxServicos(navController = navController)
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    BoxSelecaobarbeiro(navController = navController)
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    BoxSelecaoDataEHora(navController = navController)
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

            }
        },
        bottomBar = {
            BottomBarCustom(navController)
        }
    )
}

@Composable
fun BoxServicos(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)  // Espaço entre os itens
        ) {
            Text(
                text = "Serviços",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            // Duas caixas de serviço
            CardServicosTelaAgendamento(titulo = "Corte", descricao = "Corte simples de cabelo" , preco = 25.00 )
            CardServicosTelaAgendamento(titulo = "Corte + Escova", descricao = "Corte + escova" , preco = 55.00 )
        }
    }
}

@Composable
fun BoxSelecaobarbeiro(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)  // Espaço entre os itens
        ) {
            Text(
                text = "Selecione o barbeiro",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            // Bloco para adicionar CirculoBarbeiroAgendamento
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),  // Adiciona espaço vertical opcional
                horizontalArrangement = Arrangement.spacedBy(16.dp)  // Espaço entre os círculos
            ) {
                // Adiciona os círculos horizontalmente
                repeat(3) {
                    CirculoBarbeiroAgendamento(navController)
                }
            }
        }
    }
}

@Composable
fun BoxSelecaoDataEHora(navController: NavController) {
    var selectedHorario by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)  // Espaço entre as seções
    ) {
        // Primeira separação: Inputs para seleção de dia e mês
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp) // Espaço entre os inputs
        ) {
            Text(
                text = "Selecione o dia e o mês",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp) // Espaço entre os inputs
            ) {
                // Input para seleção do dia
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Dia") },
                    modifier = Modifier.weight(1f) // Preenche o espaço disponível
                )

                // Input para seleção do mês
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Mês") },
                    modifier = Modifier.weight(1f) // Preenche o espaço disponível
                )
            }
        }

        // Segunda separação: Opções de horário com duas linhas
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp) // Espaço entre as linhas
        ) {
            Text(
                text = "Selecione o horário",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            // Lista de horários como botões em duas linhas
            val horarios = listOf("08:00", "09:00", "10:00", "11:00", "12:00", "13:00")
            Column {
                // Primeira linha de horários
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center // Centraliza os botões na linha
                ) {
                    horarios.take(3).forEach { horario ->
                        ButtomSelecaoHorario(
                            horario = horario,
                            isSelected = horario == selectedHorario,
                            onClick = {
                                selectedHorario = if (selectedHorario == horario) null else horario
                            }
                        )
                    }
                }

                // Segunda linha de horários
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center // Centraliza os botões na linha
                ) {
                    horarios.drop(3).forEach { horario ->
                        ButtomSelecaoHorario(
                            horario = horario,
                            isSelected = horario == selectedHorario,
                            onClick = {
                                selectedHorario = if (selectedHorario == horario) null else horario
                            }
                        )
                    }
                }
            }
        }
    }
}




@Composable
fun CardServicosTelaAgendamento(
    titulo: String,
    descricao: String,
    preco: Double
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.Transparent)
            .border(2.5.dp, Color.Gray, RoundedCornerShape(15.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = titulo,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = BLUE_PRIMARY,
                    modifier = Modifier.padding(bottom = 4.dp) // Espaço abaixo do título
                )
                Text(
                    text = descricao,
                    color = BLUE_PRIMARY,
                    fontSize = 16.sp,
                )
            }

            Box(
                modifier = Modifier
                    .height(100.dp)
                    .width(67.dp)
                    .background(BLUE_PRIMARY, CircleShape)
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text = "R$ ${preco.format(2)}",
                    color = ORANGE_SECUNDARY,
                    fontSize = 15.sp, // Tamanho da fonte dentro do círculo
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

// Função de extensão para formatar Double como string com duas casas decimais
fun Double.format(digits: Int) = "%.${digits}f".format(this)


@Composable
fun CirculoBarbeiroAgendamento(navController: NavController) {
    Box(
        modifier = Modifier
            .size(100.dp)  // Define o tamanho fixo para criar um círculo
            .clip(CircleShape)  // Define a forma circular
            .background(Color.Transparent)  // Define a cor de fundo como verde
            .border(3.dp, Color.Gray, CircleShape)  // Adiciona uma borda opcional para visualização
    ) {
        Text(
            text = "Foto Barbeiro",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)  // Alinha o texto no centro
        )
    }
}

@Composable
fun ButtomSelecaoHorario(
    horario: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) Color.Blue else Color.Gray
    val textColor = if (isSelected) Color.White else Color.Black

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        modifier = Modifier
            .width(100.dp)  // Define a largura do botão
            .height(60.dp)  // Define a altura do botão
            .padding(4.dp)  // Adiciona padding ao redor do botão
    ) {
        Text(
            text = horario,
            color = textColor,
            fontSize = 16.sp
        )
    }
}

@Preview
@Composable
fun AgendamentoPreview() {
    Agendamento(navController = rememberNavController())
}
