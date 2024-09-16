package com.example.na_regua_app.view

import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.R
import com.example.na_regua_app.classes.Funcionario
import com.example.na_regua_app.classes.Servico
import com.example.na_regua_app.components.Botao
import com.example.na_regua_app.components.BottomBarCustom
import com.example.na_regua_app.components.SelecaoFuncionarios
import com.example.na_regua_app.components.ServiceList
import com.example.na_regua_app.components.TopBarCustom
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.ui.theme.Typography
import com.example.na_regua_app.ui.theme.labelLargeOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Agendamento(
    navController: NavController
) {
    var selectedService by remember { mutableStateOf<String?>(null) }
    var selectedBarbeiro by remember { mutableStateOf<String?>(null) }
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
                    BoxSelecaobarbeiro(navController = navController) { barbeiroName ->
                        selectedBarbeiro = barbeiroName
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
            barbeiro = selectedBarbeiro!!,
            date = selectedDate!!,
            time = selectedTime!!,
            value = 24.90,
            onDismiss = { showDialog = false },
            onConfirm = {
                navController.navigate("agendaUsuario") // Navega para a tela agendaUsuario
            }
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
    onDismiss: () -> Unit,
    onConfirm: () -> Unit // Adicionado callback para confirmação
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                "Resumo do Agendamento",
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                color = BLUE_PRIMARY,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Column(modifier = Modifier.padding(start = 22.dp)){
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Você selecionou:\n")
                        }
                        append("\n") // Espaço entre o título e os itens

                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Serviço: ")
                        }
                        append(service)

                        append("\n")

                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Barbeiro: ")
                        }
                        append(barbeiro)

                        append("\n")

                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Dia: ")
                        }
                        append(date)

                        append("\n")

                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Horário: ")
                        }
                        append(time)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "O valor do seu atendimento ficou em R$ ${value.format(2)}.",
                    modifier = Modifier.width(200.dp)
                )
            }
        },
        confirmButton = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Botao(onClick = {
                    onConfirm() // Chama o callback de confirmação para navegação
                }, textButton = "Confirmar")
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
    var selectedService by remember { mutableStateOf<Servico?>(null) }

    val servicos = listOf(
        Servico(
            tituloServico = "Corte",
            descricao = "Corte simples de cabelo",
            preco = 25.00,
        ),
        Servico(
            tituloServico = "Corte + Escova",
            descricao = "Corte + escova",
            preco = 55.00,
        )
    )

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
            ServiceList(
                services = servicos,
                isSelectable = true,
                selectedService = selectedService,
                onServiceClick = { service ->
                    selectedService = service
                    onServiceSelected(service.tituloServico)  // Retorna o título do serviço selecionado
                }
            )
        }
    }
}

@Composable
fun BoxSelecaobarbeiro(
    navController: NavController,
    onBarbeiroSelected: (String) -> Unit
) {
    var selectedBarbeiro by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Selecione o barbeiro",
            style = Typography.titleMedium,
        )

        val funcionarios = listOf(
            Funcionario(1, nome = "Barbeiro 1", imgPerfil = R.drawable.foto_perfil, dtype = "Barbeiro", email = "barbeiro@gmail.com"),
            Funcionario(2, nome = "Barbeiro 2", imgPerfil = R.drawable.barbeira2, dtype = "Barbeiro", email = "barbeiro@gmail.com"),
            Funcionario(3, nome = "Barbeiro 3", imgPerfil = R.drawable.barbeiro1, dtype = "Barbeiro", email = "barbeiro@gmail.com")

        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(3) { index ->
                SelecaoFuncionarios(
                    funcionario = funcionarios[index],
                    isSelectable = true,
                    isSelected = selectedBarbeiro == funcionarios[index].nome,
                    onClick = {
                        selectedBarbeiro = funcionarios[index].nome
                        onBarbeiroSelected(selectedBarbeiro!!)
                    }
                )
            }
        }
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
