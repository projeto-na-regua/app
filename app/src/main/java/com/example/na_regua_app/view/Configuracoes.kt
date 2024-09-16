
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.na_regua_app.R
import com.example.na_regua_app.classes.Usuario
import com.example.na_regua_app.components.BotaoComIcone
import com.example.na_regua_app.components.BottomBarCustom
import com.example.na_regua_app.components.TopBarCustom
import com.example.na_regua_app.ui.theme.textSection

@Composable
fun Configuracoes(
    navController: NavController,
    usuario: Usuario
) {

    val isAdm = remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopBarCustom(navController, "Configurações", false, false, true)
        },
        content = { paddingValues ->
            Column (
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    SelecionarConfiguracao(
                        text = "Informações da conta",
                        onClick = { navController.navigate("settingsprofile") },
                        icon = Icons.Default.AccountCircle
                    )

                    if (isAdm.value) {
                        SelecionarConfiguracao(
                            text = "Seu negócio",
                            onClick = { navController.navigate("settingsbusiness") },
                            icon = Icons.Default.Build
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        BotaoComIcone(
                            onClick = { /*TODO*/ },
                            textButton = "Sair",
                            imagePainter = painterResource(id = R.drawable.icon_exit),
                            )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = "Desenvolvido por NaRégua", style = textSection)
                    }
                }
            }
        },
        bottomBar = {
            BottomBarCustom(navController, usuario)
        }
    )
}

@Composable
fun SelecionarConfiguracao (
    text: String,
    onClick: () -> Unit,
    icon: ImageVector
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .border(1.dp, Color(0xFFCBD5E0), RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            Modifier
                .padding(16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color(0xFF082031)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = text,
                color = Color(0xFF082031)
            )
        }

        Row {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Seta direita",
                tint = Color(0xFF082031),
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}
