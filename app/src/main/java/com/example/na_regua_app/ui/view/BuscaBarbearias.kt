import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.net.Uri
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.ContentAlpha
import com.example.na_regua_app.R
import com.example.na_regua_app.ui.components.Botao
import com.example.na_regua_app.ui.components.TopBarCustom
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuscaBarbearias(navController: NavHostController) {
    var servico by remember { mutableStateOf("") }
    var dataSelecionada by remember { mutableStateOf("") }
    var horaSelecionada by remember { mutableStateOf("") }
    var dataParaBackend by remember { mutableStateOf("") }
    var horaParaBackend by remember { mutableStateOf("") }
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    Scaffold(
        topBar = {
            TopBarCustom(navController, "Busca", true)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Campo para selecionar serviço
                OutlinedTextField(
                    value = servico,
                    onValueChange = { servico = it },
                    label = { Text("Selecionar serviço") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.tesourinha),
                            contentDescription = "Selecionar serviço"
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor = Color.LightGray
                    )
                )

                // Botão para selecionar data
                OutlinedTextField(
                    value = dataSelecionada,
                    onValueChange = {}, // Não permite entrada de texto
                    label = { Text("Selecionar data") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Selecionar data"
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            // Abre o DatePickerDialog ao clicar no ícone
                            val datePickerDialog = DatePickerDialog(
                                context,
                                { _, year, month, dayOfMonth ->
                                    val selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year)
                                    dataSelecionada = selectedDate // Atualiza a data selecionada

                                    // Formato para o backend
                                    dataParaBackend = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
                                },
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH)
                            )
                            datePickerDialog.show()
                        }) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Selecionar data"
                            )
                        }
                    },
                    readOnly = true, // Não permite edição
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor = Color.LightGray
                    )
                )

                // Campo para selecionar hora
                OutlinedTextField(
                    value = horaSelecionada,
                    onValueChange = {}, // Não permite entrada de texto
                    label = { Text("Selecionar hora") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.icone_hora),
                            contentDescription = "Selecionar hora",
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            // Abre o TimePickerDialog ao clicar no ícone
                            val timePickerDialog = TimePickerDialog(
                                context,
                                { _, hourOfDay, minute ->
                                    val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                                    horaSelecionada = selectedTime // Atualiza a hora selecionada

                                    // Formato para o backend
                                    horaParaBackend = String.format("%02d:%02d:%02d", hourOfDay, minute, 0)
                                },
                                calendar.get(Calendar.HOUR_OF_DAY),
                                calendar.get(Calendar.MINUTE),
                                true // Formato 24 horas
                            )
                            timePickerDialog.show()
                        }) {
                            Image(
                                painter = painterResource(id = R.drawable.icone_hora),
                                contentDescription = "Selecionar hora",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    readOnly = true, // Não permite edição
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor = Color.LightGray
                    )
                )

                Spacer(modifier = Modifier.weight(1f))
                Botao(onClick = {
                    if (servico.isNotEmpty() && dataSelecionada.isNotEmpty() && horaSelecionada.isNotEmpty()) {
                        // Passa os dados no formato que o backend espera
                        val route = "listagemBarbearias/${Uri.encode(servico)}/${Uri.encode(dataParaBackend)}/${Uri.encode(horaParaBackend)}"
                        println("Navegando para: $route")
                        navController.navigate(route)
                    } else {
                        println("Por favor, preencha todos os campos antes de pesquisar.")
                    }
                }, textButton = "Pesquisar")

                Spacer(modifier = Modifier.weight(.05f))
            }
        }
    )
}

@Preview
@Composable
fun MyAppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController, startDestination = "buscaBarbearias") {
        composable("buscaBarbearias") { BuscaBarbearias(navController) }
    }
}
