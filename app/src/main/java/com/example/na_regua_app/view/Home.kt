
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
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
fun Home(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopBarCustom(navController, "Home", true)
        },
        content = { paddingValues ->
            HomeContent(
                paddingValues = paddingValues
            )
        },
        bottomBar = {
            BottomBarCustom(navController)
        }
    )
}


@Composable
fun HomeContent(paddingValues: PaddingValues) {
    // Definindo o estado do nome do usuário
    var nomeUsuario by remember { mutableStateOf("Melissa") }
    var agendamentosProximos by remember { mutableIntStateOf(2) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color.White)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BLUE_PRIMARY)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BLUE_PRIMARY)
                    .padding(20.dp)
                    .height(120.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Bom dia, ")
                        withStyle(style = SpanStyle(color = ORANGE_SECUNDARY)) { append(nomeUsuario) }
                        append("!")
                    },
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.size(10.dp))

                MiddleText(
                    text = "Abaixo estão as informações do dia.",
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = 60.dp)
                    .size(130.dp)
                    .clip(RoundedCornerShape(50)) // Círculo completo, incluindo borda
                    .background(Color.Transparent) // Transparente fora da borda
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(
                            width = 10.dp,
                            color = ORANGE_SECUNDARY,
                            shape = RoundedCornerShape(50)
                        )
                        .clip(RoundedCornerShape(50)) // Clip interno para respeitar a borda circular
                        .background(Color.White) // Fundo branco dentro da borda laranja
                ) {
                    Text(
                        text = "$agendamentosProximos",
                        color = BLUE_PRIMARY,
                        fontSize = 40.sp,
                        modifier = Modifier.align(Alignment.Center),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun HomePreview() {
    val navController = rememberNavController()
    Home(navController = navController)
}


@Composable
fun TitleText(text: String, color: Color){

    Text(text = text,
        color = color,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold)
}

@Composable
fun MiddleText(text: String){

    Text(text = text,
        color = White,
        fontSize = 18.sp,
        fontWeight = FontWeight.Light)
}

