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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.components.Botao
import com.example.na_regua_app.components.BottomBarCustom
import com.example.na_regua_app.components.TopBarCustom
import com.example.na_regua_app.ui.theme.Typography
import com.example.na_regua_app.view.BoxSelecaoDataEHora
import com.example.na_regua_app.view.BoxSelecaobarbeiro
import com.example.na_regua_app.view.BoxServicos
import com.example.na_regua_app.view.ConfirmationDialog

@Composable
fun BuscaBarbearias(navController: NavController) {
    var selectedService by remember { mutableStateOf<String?>(null) }
    var selectedDate by remember { mutableStateOf<String?>(null) }
    var selectedLocal by remember { mutableStateOf<String?>(null) }

    var showServiceDialog by remember { mutableStateOf(false) }
    var showDateDialog by remember { mutableStateOf(false) }
    var showLocalDialog by remember { mutableStateOf(false) }

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
                        onServiceClick = { showServiceDialog = true },
                        onDateClick = { showDateDialog = true },
                        onLocalClick = { showLocalDialog = true }
                    )
                }
            }
        },
        bottomBar = {
            BottomBarCustom(navController)
        }
    )

    // Diálogo para selecionar o serviço
    if (showServiceDialog) {
        ModalDialog(
            title = "Selecionar Serviço",
            options = listOf("Corte", "Barba", "Corte + Barba"),
            onDismiss = { showServiceDialog = false },
            onOptionSelected = { service ->
                selectedService = service
                showServiceDialog = false
            }
        )
    }

    // Diálogo para selecionar a data
    if (showDateDialog) {
        ModalDialog(
            title = "Selecionar Data",
            options = listOf("10/09/2024", "11/09/2024", "12/09/2024"),
            onDismiss = { showDateDialog = false },
            onOptionSelected = { date ->
                selectedDate = date
                showDateDialog = false
            }
        )
    }

    // Diálogo para selecionar o local
    if (showLocalDialog) {
        ModalDialog(
            title = "Selecionar Local",
            options = listOf("Barbearia Central", "Barbearia Norte", "Barbearia Sul"),
            onDismiss = { showLocalDialog = false },
            onOptionSelected = { local ->
                selectedLocal = local
                showLocalDialog = false
            }
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
fun ModalDialog(
    title: String,
    options: List<String>,
    onDismiss: () -> Unit,
    onOptionSelected: (String) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = title) },
        text = {
            Column {
                options.forEach { option ->
                    Text(
                        text = option,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable { onOptionSelected(option) }
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Fechar")
            }
        }
    )
}

@Preview
@Composable
fun BuscaBarbeariasPreview() {
    BuscaBarbearias(navController = rememberNavController())
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



