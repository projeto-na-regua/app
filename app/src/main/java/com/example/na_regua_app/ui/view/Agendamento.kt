package com.example.na_regua_app.ui.view

import android.util.Log
import android.widget.CalendarView
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.data.model.Funcionario
import com.example.na_regua_app.data.model.ServicoCardDTO
import com.example.na_regua_app.data.model.usuarios
import com.example.na_regua_app.ui.components.Botao
import com.example.na_regua_app.ui.components.BottomBarCustom
import com.example.na_regua_app.ui.components.SelecaoFuncionarios
import com.example.na_regua_app.ui.components.ServiceList
import com.example.na_regua_app.ui.components.TopBarCustom
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.ui.theme.Typography
import com.example.na_regua_app.viewmodel.AgendamentoViewModel
import com.example.na_regua_app.viewmodel.FuncionarioViewModel
import com.example.na_regua_app.viewmodel.PerfilBarbeariaViewModel
import com.example.na_regua_app.viewmodel.ServicoViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Agendamento(
    navController: NavController,
    idBarbearia: Int,
    isBarbeiro: Boolean,
    servicoViewModel: ServicoViewModel = koinViewModel(),
    funcionarioViewModel: FuncionarioViewModel = koinViewModel(),
    agendamentoViewModel: AgendamentoViewModel = koinViewModel()
) {
    var selectedService by remember { mutableStateOf<ServicoCardDTO?>(null) }
    var selectedBarbeiro by remember { mutableStateOf<Funcionario?>(null) }
    var selectedDate by remember { mutableStateOf<String?>(null) }
    var selectedTime by remember { mutableStateOf<String?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        servicoViewModel.obterServicosPorStatus(status = "active", idBarbearia = idBarbearia, isBarbeiro = isBarbeiro)
        funcionarioViewModel.obterFuncionarios(idBarbearia = idBarbearia, isBarbeiro = isBarbeiro)
    }

    val servicos by servicoViewModel.servicos.collectAsState()
    val funcionarios by funcionarioViewModel.funcionarios.collectAsState()
    val horarios by agendamentoViewModel.horarios.collectAsState()

    Scaffold(
        topBar = {
            TopBarCustom(navController, "Agendamento", true)
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(14.dp)
            ) {
                item {
                    BoxServicos(navController = navController, servicos) { service ->
                        selectedService = service
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    Text(
                        text = "Selecione o barbeiro",
                        color = BLUE_PRIMARY,
                        style = Typography.titleMedium,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                }

                item {
                    funcionarios?.let {
                        BoxSelecaoBarbeiro(navController = navController, it) { barbeiroName ->
                            selectedBarbeiro = barbeiroName
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                    item {
                        selectedBarbeiro?.id?.let { barbeiroId ->
                            selectedService?.id?.let { servicoId ->
                                BoxSelecaoDataEHora(
                                    navController = navController,
                                    agendamentoViewModel = agendamentoViewModel,
                                    selectedBarbearia = idBarbearia,
                                    selectedBarbeiro = barbeiroId,
                                    selectedServico = servicoId
                                ) { date, time ->
                                    selectedDate = date
                                    selectedTime = time
                                }
                            }
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
            BottomBarCustom(navController, LocalContext.current)
        }
    )

    if (showDialog) {
        ConfirmationDialog(
            service = selectedService!!.tituloServico,
            barbeiro = selectedBarbeiro!!.nome,
            date = selectedDate!!,
            time = selectedTime!!,
            value = selectedService!!.preco,
            onDismiss = { showDialog = false }
        ) {
            agendamentoViewModel.adicionarAgendamento(
                selectedDate!!,
                selectedTime!!,
                selectedService!!.id,
                selectedBarbeiro!!.id,
                idBarbearia
            ) { success ->

                if (success) {
                    Log.d("Agendamento", "Agendamento realizado com sucesso!")
                    Toast.makeText(context, "Agendamento realizado com sucesso!", Toast.LENGTH_SHORT).show()
                    navController.navigate("agendaUsuarios") {
                        popUpTo("agendaUsuarios") { inclusive = true }
                    }
                } else {
                    Log.d("Agendamento", "Erro ao agendar.")
                    Toast.makeText(context, "Erro ao agendar. Tente novamente.", Toast.LENGTH_SHORT).show()
                }
            }
        }
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
    onConfirm: () -> Unit
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
    servicos: List<ServicoCardDTO>,
    onServiceSelected: (ServicoCardDTO) -> Unit // Altere para passar o objeto completo
) {
    var selectedService by remember { mutableStateOf<ServicoCardDTO?>(null) }

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
                ServiceList(
                    services = servicos,
                    isSelectable = true,
                    selectedService = selectedService,
                    onServiceClick = { service ->
                        selectedService = service
                        onServiceSelected(service)
                    }
                )
            }
        }
    }
}

@Composable
fun BoxSelecaoBarbeiro(
    navController: NavController,
    funcionarios: List<Funcionario>,
    onBarbeiroSelected: (Funcionario) -> Unit
) {
    var selectedBarbeiro by remember { mutableStateOf<Funcionario?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            funcionarios.forEach { funcionario ->
                item {
                    SelecaoFuncionarios(
                        funcionario = funcionario,
                        isSelectable = true,
                        isSelected = selectedBarbeiro == funcionario,
                        onClick = {
                            selectedBarbeiro = funcionario
                            onBarbeiroSelected(selectedBarbeiro!!)
                        }
                    )
                }
            }
        }

    }
}

@Composable
fun BoxSelecaoDataEHora(
    navController: NavController,
    agendamentoViewModel: AgendamentoViewModel,
    selectedBarbeiro: Int,
    selectedServico: Int,
    selectedBarbearia: Int,
    onDateTimeSelected: (String, String) -> Unit
) {
    var selectedDate by remember { mutableStateOf<String?>(null) }
    var selectedTime by remember { mutableStateOf<String?>(null) }
    val horarios by agendamentoViewModel.horarios.collectAsState()

    // Defina a altura do LazyColumn para evitar altura infinita
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 500.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Selecione o dia e o mês",
                style = Typography.titleMedium
            )
        }

        item {
            CalendarExample(
                onDateSelected = { date ->
                    selectedDate = date
                    selectedTime = null  // Limpa o horário selecionado anterior
                    agendamentoViewModel.listarHorariosDisponiveis(
                        barbeiro = selectedBarbeiro,
                        servico = selectedServico,
                        barbearia = selectedBarbearia,
                        date = date
                    )
                }
            )
        }

        if (horarios.isNotEmpty() && selectedDate != null) {
            item {
                Text(
                    text = "Selecione o horário",
                    style = Typography.titleMedium
                )
            }

            horarios.chunked(3).forEach { rowHorarios ->
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        rowHorarios.forEach { horario ->
                            ButtomSelecaoHorario(
                                horario = horario.hora,
                                isSelected = horario.hora == selectedTime,
                                onClick = {
                                    selectedTime = horario.hora
                                    onDateTimeSelected(selectedDate!!, selectedTime!!)
                                }
                            )
                        }
                    }
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

fun Agendamento(navController: NavHostController) {
    TODO("Not yet implemented")
}
