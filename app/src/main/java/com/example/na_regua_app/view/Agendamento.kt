package com.example.na_regua_app.view

import android.app.DatePickerDialog
import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.components.BottomBarCustom
import com.example.na_regua_app.components.TopBarCustom
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.ui.theme.Typography
import java.util.Calendar
import com.example.na_regua_app.components.Botao
import com.example.na_regua_app.ui.theme.labelLargeOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Agendamento(
    navController: NavController
) {
    var selectedService by remember { mutableStateOf<String?>(null) }
    var selectedBarbeiro by remember { mutableStateOf<Int?>(null) }
    var selectedDate by remember { mutableStateOf<String?>(null) }
    var selectedTime by remember { mutableStateOf<String?>(null) }
    var showDialog by remember { mutableStateOf(false) }

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
                    BoxServicos(navController = navController) { service ->
                        selectedService = service
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    BoxSelecaobarbeiro(navController = navController) { barbeiro ->
                        selectedBarbeiro = barbeiro
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    BoxSelecaoDataEHora(navController = navController) { date, time ->
                        selectedDate = date
                        selectedTime = time
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    Botao(
                        onClick = {
                            if (selectedService != null && selectedBarbeiro != null && selectedDate != null && selectedTime != null) {
                                showDialog = true
                            }
                        },
                        textButton = "Marcar Horário"
                    )
                }
            }
        },
        bottomBar = {
            BottomBarCustom(navController)
        }
    )

    if (showDialog) {
        ConfirmationDialog(
            service = selectedService!!,
            barbeiro = "Barbeiro ${selectedBarbeiro!! + 1}",
            date = selectedDate!!,
            time = selectedTime!!,
            value = 24.90,
            onDismiss = { showDialog = false }
        )
    }
}

@Composable
fun ConfirmationDialog(
    service: String,
    barbeiro: String,
    date: String,
    time: String,
    value: Double,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Resumo do Agendamento") },
        text = {
            Column {
                Text("Você selecionou:")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Serviço: $service")
                Text("Barbeiro: $barbeiro")
                Text("Dia: $date")
                Text("Horário: $time")
                Spacer(modifier = Modifier.height(8.dp))
                Text("O valor do seu atendimento ficou em R$ ${value.format(2)}.")
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss
            ) {
                Text("Confirmar", style = labelLargeOrange)
            }
        }
    )
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)

@Composable
fun BoxServicos(
    navController: NavController,
    onServiceSelected: (String) -> Unit
) {
    var selectedService by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Serviços",
                style = Typography.titleMedium
            )

            CardServicosTelaAgendamento(
                titulo = "Corte",
                descricao = "Corte simples de cabelo aaaa aaaaa aaaaa aaaa aaaaa aaaa",
                preco = 25.00,
                isSelected = selectedService == "Corte",
                onClick = {
                    selectedService = "Corte"
                    onServiceSelected(selectedService!!)
                }
            )
            CardServicosTelaAgendamento(
                titulo = "Corte + Escova",
                descricao = "Corte + escova",
                preco = 55.00,
                isSelected = selectedService == "Corte + Escova",
                onClick = {
                    selectedService = "Corte + Escova"
                    onServiceSelected(selectedService!!)
                }
            )
        }
    }
}

@Composable
fun CardServicosTelaAgendamento(
    titulo: String,
    descricao: String,
    preco: Double,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) ORANGE_SECUNDARY else Color.Transparent

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(backgroundColor)  // Alterar a cor de fundo com base na seleção
            .border(2.5.dp, Color.Gray, RoundedCornerShape(15.dp))
            .clickable { onClick() }  // Chama o callback ao clicar
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 7.dp)
            ) {
                Text(
                    text = titulo,
                    style = Typography.labelLarge,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = descricao,
                    style = Typography.labelMedium,
                )
            }

            Box(
                modifier = Modifier
                    .height(80.dp)
                    .width(75.dp)
                    .background(BLUE_PRIMARY, CircleShape)
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text = "R$ ${preco.format(2)}",
                    color = ORANGE_SECUNDARY,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}


@Composable
fun BoxSelecaobarbeiro(
    navController: NavController,
    onBarbeiroSelected: (Int) -> Unit
) {
    var selectedBarbeiro by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Selecione o barbeiro",
            style = Typography.titleMedium,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(3) { index ->
                CirculoBarbeiroAgendamento(
                    isSelected = selectedBarbeiro == index,
                    onClick = {
                        selectedBarbeiro = index
                        onBarbeiroSelected(selectedBarbeiro!!)
                    }
                )
            }
        }
    }
}

@Composable
fun CirculoBarbeiroAgendamento(
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) ORANGE_SECUNDARY else Color.Gray

    Box(
        modifier = Modifier
            .size(85.dp)
            .clip(CircleShape)
            .background(Color.Transparent)
            .border(3.dp, borderColor, CircleShape)
            .clickable { onClick() }
    ) {
        Text(
            text = "Foto Barbeiro",
            style = Typography.labelMedium,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun BoxSelecaoDataEHora(
    navController: NavController,
    onDateTimeSelected: (String, String) -> Unit
) {
    var selectedDate by remember { mutableStateOf<String?>(null) }
    var selectedTime by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Selecione o dia e o mês",
            style = Typography.titleMedium
        )

        CalendarExample(
            onDateSelected = { date ->
                selectedDate = date
                if (selectedTime != null) {
                    onDateTimeSelected(selectedDate!!, selectedTime!!)
                }
            }
        )

        Text(
            text = "Selecione o horário",
            style = Typography.titleMedium
        )

        val horarios = listOf("08:00", "09:00", "10:00", "11:00", "12:00", "13:00")
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                horarios.take(3).forEach { horario ->
                    ButtomSelecaoHorario(
                        horario = horario,
                        isSelected = horario == selectedTime,
                        onClick = {
                            selectedTime = horario
                            if (selectedDate != null) {
                                onDateTimeSelected(selectedDate!!, selectedTime!!)
                            }
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(7.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                horarios.drop(3).forEach { horario ->
                    ButtomSelecaoHorario(
                        horario = horario,
                        isSelected = horario == selectedTime,
                        onClick = {
                            selectedTime = horario
                            if (selectedDate != null) {
                                onDateTimeSelected(selectedDate!!, selectedTime!!)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ButtomSelecaoHorario(
    horario: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) ORANGE_SECUNDARY else BLUE_PRIMARY
    val textColor = if (isSelected) BLUE_PRIMARY else ORANGE_SECUNDARY

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        modifier = Modifier
            .width(90.dp)
            .height(50.dp),
        shape = RoundedCornerShape(12.dp),
    ) {
        Text(
            text = horario,
            color = textColor,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CalendarExample(
    onDateSelected: (String) -> Unit
) {
    var selectedDate by remember { mutableStateOf("") }

    Column {
        // Exibindo o calendário na tela
        AndroidView(
            factory = { context ->
                CalendarView(context).apply {
                    setOnDateChangeListener { _, year, month, dayOfMonth ->
                        selectedDate = "$dayOfMonth/${month + 1}/$year"
                        onDateSelected(selectedDate)
                    }
                }
            },
            update = { view ->
                // Aqui você pode atualizar o calendário se necessário
            }
        )

        // Exibe a data selecionada
        if (selectedDate.isNotEmpty()) {
            Text(text = "Data selecionada: $selectedDate", style = Typography.labelSmall)
        }
    }
}

@Preview
@Composable
fun AgendamentoPreview() {
    Agendamento(navController = rememberNavController())
}
