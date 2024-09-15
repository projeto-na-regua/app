import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.na_regua_app.R
import com.example.na_regua_app.components.BotaoComIcone
import com.example.na_regua_app.components.BottomBarCustom
import com.example.na_regua_app.components.TopBarCustom
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.textAlert
import com.example.na_regua_app.ui.theme.textSection

@Composable
fun ExcluirNegocio(
    navController: NavController
) {
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

                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painter = painterResource(id = R.drawable.icon_alert), contentDescription = "Imagem de exclução de conta", modifier = Modifier.size(100.dp))

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Deseja excluir sua barbearia?",
                        style = textAlert,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Você está prestes a excluir sua barbearia permanentemente. Isso significa que todos os seus dados serão apagados e não poderão ser recuperados.",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        textAlign = TextAlign.Center,
                        color = BLUE_PRIMARY,
                        fontWeight = FontWeight.SemiBold
                    )
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
                            textButton = "Excuir barbearia",
                            imagePainter = painterResource(id = R.drawable.group_358),
                            backgroundColor = Color(0xFFCC2828),
                            textColor = Color(0xFF591919)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = "Desenvolvido por NaRégua", style = textSection)
                    }
                }
            }
        },
        bottomBar = {
            BottomBarCustom(navController)
        }
    )
}
