package com.example.na_regua_app.view
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.classes.Usuario
import com.example.na_regua_app.classes.usuarios
import com.example.na_regua_app.components.BottomBarCustom
import com.example.na_regua_app.components.TopBarCustom
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY

@Composable
fun AgendaUsuario(
    navController: NavController,
    usuario: Usuario
) {
    var botaoClicado by remember { mutableStateOf(false) }
    var nomeBarbearia by remember { mutableStateOf("") }
    var descricaoBarbearia by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.background(White),
        topBar = {
            TopBarCustom(navController, "Agendamentos", true)
        },
        content = { paddingValues ->
            AgendaUsuarioContent(
                paddingValues = paddingValues,
                navController = navController
            )
        },
        bottomBar = {
            BottomBarCustom(navController, usuario)
        }
    )
}

@Composable
fun AgendaUsuarioContent(paddingValues: PaddingValues, navController: NavController) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 90.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Seus agendamentos",
            fontSize = 18.sp,
            color = BLUE_PRIMARY,
            fontWeight = FontWeight(600),
            letterSpacing = .5.sp,
            modifier = Modifier.fillMaxWidth(.9f)
        )
        LazyRow (
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(top = 10.dp)
        ){
            item {
                Box(
                    modifier = Modifier
                        .border(1.dp, BLUE_PRIMARY, RoundedCornerShape(12.dp))
                        .padding(horizontal = 10.dp)
                        .padding(top = 10.dp)
                        .align(Alignment.Start)
                        .width(200.dp)
                ){
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .background(Color.Transparent)
                                .border(2.dp, color = Color(0xFF082031), RoundedCornerShape(12.dp))
                        ) {
                            Image(
                                painter = painterResource(id = com.example.na_regua_app.R.drawable.png_background),
                                contentDescription = "",
                                contentScale = ContentScale.Crop, // Isso garante que a imagem preencha o círculo corretamente
                                modifier = Modifier.fillMaxSize() // Garante que a imagem ocupe o tamanho completo do Box
                            )
                        }
                        Spacer(modifier = Modifier.weight(.1f))
                        Column (
                            modifier = Modifier
                        ){
                            Text(
                                text = "Dom bigode",
                                fontSize = 16.sp,
                                fontWeight = FontWeight(600),
                                color = BLUE_PRIMARY
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Row (
                                modifier = Modifier.fillMaxWidth(.7f),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                Text(
                                    text = "23/03/2024",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight(400),
                                    color = BLUE_PRIMARY
                                    )
                                Text(
                                    text = "-",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight(400),
                                    color = BLUE_PRIMARY)
                                Text(
                                    text = "8h",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight(400),
                                    color = BLUE_PRIMARY)
                            }
                        }
                        Spacer(modifier = Modifier.weight(.2f))
                    }
                    TextButton(onClick = { /*TODO*/ },
                        modifier = Modifier.padding(top = 40.dp)) {
                        Text(
                            text = "Confirmar agendamento",
                            color = ORANGE_SECUNDARY,
                            fontSize = 12.sp
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Image(
                            painter = painterResource(id = com.example.na_regua_app.R.drawable.icone_setinha_baixo),
                            contentDescription = "",
                            Modifier.size(15.dp)
                        )
                    }
                }
            }
        }

        Text(
            text = "Histórico",
            fontSize = 18.sp,
            color = BLUE_PRIMARY,
            fontWeight = FontWeight(600),
            letterSpacing = .5.sp,
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(top = 40.dp)
        )
        LazyRow (
            modifier = Modifier.fillMaxWidth(.9f)
        ){
            items (5){
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Box(
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .padding(horizontal = 15.dp)
                            .size(100.dp)
                            .clip(CircleShape) // Certifica-se de que o Box seja recortado como um círculo
                            .background(Color.Transparent)
                            .border(1.dp, color = Color(0xFF9E9E9E), CircleShape)
                    ) {
                        Image(
                            painter = painterResource(id = com.example.na_regua_app.R.drawable.png_background),
                            contentDescription = "",
                            contentScale = ContentScale.Crop, // Isso garante que a imagem preencha o círculo corretamente
                            modifier = Modifier.fillMaxSize() // Garante que a imagem ocupe o tamanho completo do Box
                        )
                    }
                    Text(
                        text = "Dom bigode",
                        color = BLUE_PRIMARY,
                        fontSize = 15.sp,
                        letterSpacing = 1.sp,
                        fontWeight = FontWeight(500),
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun AgendaUsuarioPreview() {
    AgendaUsuario(navController = rememberNavController(), usuarios()[1])
}
