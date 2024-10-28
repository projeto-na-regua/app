package com.example.na_regua_app.ui.view.dashboard

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.na_regua_app.data.model.Barbearia
import com.example.na_regua_app.data.model.DiaSemana
import com.example.na_regua_app.data.model.ItemMenuDropDown
import com.example.na_regua_app.data.model.criarListaDiaSemana
import com.example.na_regua_app.ui.components.DropDownMenu
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.viewmodel.PerfilBarbeariaViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GerenciamentoContent(
    perfilBarbeariaViewModel: PerfilBarbeariaViewModel = koinViewModel(),
) {
    val barbearia by perfilBarbeariaViewModel.barbearia.collectAsState(initial = null)
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        perfilBarbeariaViewModel.obterBarbearia(true, 0)
    }

    if (barbearia == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Carregando...", fontSize = 20.sp, color = BLUE_PRIMARY)
        }
    } else {
        var semana by remember {
            mutableStateOf(criarListaDiaSemana(barbearia?.diaSemanas ?: emptyList()))
        }

        // Inicializa a nova lista de horários com todos os dias da semana
        val novaListaDeHorarios = remember {
            mutableStateListOf<DiaSemana>().apply {
                addAll(semana.map { it.copy() }) // Cópia de cada dia para evitar referências diretas
            }
        }

        // Use LazyColumn para exibir os dias da semana
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            item {
                Text(
                    text = "Horário de funcionamento",
                    color = BLUE_PRIMARY,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(15.dp))
            }

            items(semana) { dia ->
                Log.d("GerenciamentoContent", "Dia: ${dia.nome}, Hora Abertura: ${dia.horaAbertura}, Hora Fechamento: ${dia.horaFechamento}")
                CardDiaSemana(
                    dia = dia,
                    onStatusChanged = { updatedDia ->
                        Log.d("GerenciamentoContent", "Atualizando dia: ${updatedDia.nome} com Abertura: ${updatedDia.horaAbertura} e Fechamento: ${updatedDia.horaFechamento}")

                        // Atualiza a lista de horários
                        semana = semana.map {
                            if (it.id == updatedDia.id) {
                                novaListaDeHorarios.removeIf { it.id == updatedDia.id } // Remove o dia anterior se existir
                                novaListaDeHorarios.add(updatedDia) // Adiciona o dia atualizado
                                updatedDia // Retorna o dia atualizado
                            } else it
                        }
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        Log.d("GerenciamentoContent", "Atualizando horários com a lista: $novaListaDeHorarios")
                        if (novaListaDeHorarios.isEmpty()) {
                            Log.d("AtualizarHorario", "Lista de horários vazia!")
                            Toast.makeText(context, "Nenhum horário para atualizar.", Toast.LENGTH_SHORT).show()
                            return@Button
                        }

                        perfilBarbeariaViewModel.atualizarHorario(
                            barbearia!!.copy(emailNegocio = null),
                            novaListaDeHorarios
                        ) { success ->
                            if (success) {
                                Log.d("AtualizarHorario", "Atualização realizada com sucesso!")
                                Toast.makeText(context, "Atualização realizada com sucesso!", Toast.LENGTH_SHORT).show()
                            } else {
                                Log.d("AtualizarHorario", "Erro ao atualizar.")
                                Toast.makeText(context, "Erro ao atualizar. Tente novamente.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                ) {
                    Text(text = "Atualizar Horários", color = ORANGE_SECUNDARY)
                }
            }
        }
    }
}

@Composable
fun CardDiaSemana(
    dia: DiaSemana,
    onStatusChanged: (DiaSemana) -> Unit
) {
    // O switch deve estar desativado se horaAbertura ou horaFechamento forem null
    val isChecked = dia.horaAbertura != null && dia.horaFechamento != null
    Log.d("CardDiaSemana", "Renderizando dia ${dia.nome}, isChecked: $isChecked")

    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
    ) {
        Column {
            Text(dia.nome, fontSize = 14.sp)

            Spacer(modifier = Modifier.height(10.dp))

            // Exibir horários somente se o dia estiver ativo
            if (isChecked) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.width(300.dp)
                ) {
                    dia.horaAbertura?.let {
                        LinhaHorario(
                            "Abertura:",
                            horario = it, // Muda de dia.horaAbertura ?: "00:00:00" para dia.horaAbertura
                            onHoraFechamentoChange = {},
                            onHoraAberturaChange = { newHoraAbertura ->
                                Log.d("CardDiaSemana", "Mudança na Abertura de ${dia.nome} para $newHoraAbertura")
                                onStatusChanged(dia.copy(horaAbertura = newHoraAbertura))
                            }
                        )
                    }
                    dia.horaFechamento?.let {
                        LinhaHorario(
                            "Fechamento:",
                            horario = it, // Muda de dia.horaFechamento ?: "00:00:00" para dia.horaFechamento
                            onHoraFechamentoChange = { newHoraFechamento ->
                                Log.d("CardDiaSemana", "Mudança no Fechamento de ${dia.nome} para $newHoraFechamento")
                                onStatusChanged(dia.copy(horaFechamento = newHoraFechamento))
                            },
                            onHoraAberturaChange = {}
                        )
                    }

                }
            }
        }

        Switch(
            checked = isChecked,
            onCheckedChange = { checked ->
                Log.d("CardDiaSemana", "Switch para ${dia.nome} alterado para: $checked")
                // Ao ativar, use os horários escolhidos nos dropdowns
                val updatedDia = dia.copy(
                    horaAbertura = if (checked) dia.horaAbertura ?: "08:00:00" else null,
                    horaFechamento = if (checked) dia.horaFechamento ?: "18:00:00" else null
                )
                // Atualiza o status
                onStatusChanged(updatedDia)
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = BLUE_PRIMARY,
                uncheckedThumbColor = Color.Gray,
                checkedTrackColor = Color.White,
                uncheckedTrackColor = Color.White
            )
        )
    }
}



@Composable
fun LinhaHorario(title: String, horario: String, onHoraAberturaChange: (String) -> Unit, onHoraFechamentoChange: (String) -> Unit) {
    Log.d("LinhaHorario", "Renderizando LinhaHorario: $title com horário: $horario")
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Text(title, fontSize = 12.sp)

        Row(verticalAlignment = Alignment.CenterVertically) {
            val selectedHour = getHours(horario)
            val selectedMinute = getMinutes(horario)

            DropDownMenu(
                items = getHoursList(),
                modifier = Modifier
                    .width(80.dp)
                    .height(45.dp)
                    .border(2.dp, color = Color.Gray, shape = RoundedCornerShape(15.dp)),
                menuWidth = 100.dp,
                tamFont = 10.sp,
                tamIcon = 10.dp,
                selectedItemPosition = filterList(getHoursList(), selectedHour),
                onItemSelected = { selected ->
                    Log.d("LinhaHorario", "Hora de ${title} selecionada: ${selected.name}")
                    onHoraAberturaChange("${selected.name}:${selectedMinute.padStart(2, '0')}")
                }
            )
            Text("h ", fontSize = 10.sp, modifier = Modifier.padding(start = 3.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Text(" - ")
            Spacer(modifier = Modifier.width(10.dp))
            DropDownMenu(
                items = getMinutesList(),
                modifier = Modifier
                    .width(80.dp)
                    .height(45.dp)
                    .border(2.dp, color = Color.Gray, shape = RoundedCornerShape(15.dp)),
                menuWidth = 100.dp,
                tamFont = 10.sp,
                tamIcon = 10.dp,
                selectedItemPosition = filterList(getMinutesList(), selectedMinute),
                onItemSelected = { selected ->
                    Log.d("LinhaHorario", "Minuto de ${title} selecionado: ${selected.name}")
                    onHoraAberturaChange("${selectedHour}:${selected.name}")
                }
            )
        }
    }
}

fun getHoursList(): List<ItemMenuDropDown> =
    (0..23).map { hour -> ItemMenuDropDown(name = hour.toString().padStart(2, '0')) }

fun getMinutesList(): List<ItemMenuDropDown> =
    listOf("00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55").map { ItemMenuDropDown(name = it) }

fun filterList(listItem: List<ItemMenuDropDown>, hora: String): ItemMenuDropDown? =
    listItem.firstOrNull { it.name == hora }

fun getHours(time: String): String = time.substring(0, 2)

fun getMinutes(time: String): String = time.substring(3, 5)

@Composable
fun TituloIcon(
    title: String,
    icon: Int,
    onIconClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            color = BLUE_PRIMARY,
            fontSize = 16.sp
        )

        Box(
            modifier = Modifier
                .background(BLUE_PRIMARY, shape = RoundedCornerShape(10.dp))
                .clickable(onClick = onIconClick)
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = title,
                tint = ORANGE_SECUNDARY,
                modifier = Modifier
                    .size(50.dp)
                    .padding(10.dp)
            )
        }
    }
}
