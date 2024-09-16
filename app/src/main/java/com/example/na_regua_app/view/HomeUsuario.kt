package com.example.na_regua_app.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.R
import com.example.na_regua_app.classes.Usuario
import com.example.na_regua_app.classes.usuarios
import com.example.na_regua_app.components.BarraPesquisar
import com.example.na_regua_app.components.BottomBarCustom
import com.example.na_regua_app.components.CardImagemInfoBarbearia
import com.example.na_regua_app.components.TopBarCustom
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY

@Composable
fun HomeUsuario(
    navController: NavController,
    usuario: Usuario
) {
    var botaoClicado by remember { mutableStateOf(false) }
    var nomeBarbearia by remember { mutableStateOf("") }
    var descricaoBarbearia by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.background(White),
        topBar = {
            TopBarCustom(navController, "Home", true)
        },
        content = { paddingValues ->
            HomeUsuarioContent(
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
@OptIn(ExperimentalMaterial3Api::class)
fun HomeUsuarioContent(paddingValues: PaddingValues, navController: NavController) {
    LazyColumn(
        contentPadding = paddingValues,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        item {
            BarraPesquisar({ pesquisa -> navController.navigate("cadastro") }, navController)
        }

        item {
            Text(
                text = "Informações para você",
                color = BLUE_PRIMARY,
                fontSize = 20.sp,
                letterSpacing = .5.sp,
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .padding(vertical = 15.dp)
            )
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = BLUE_PRIMARY)
                    .padding(vertical = 15.dp, horizontal = 12.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Seu compromisso foi confirmado pela barbearia ")

                        withStyle(style = SpanStyle(color = ORANGE_SECUNDARY, fontWeight = FontWeight.Bold)) {
                            append("Dom Bigode")
                        }

                        append(". \nInformações do Agendamento: ")

                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Corte + Escova")
                        }

                        append(" no dia ")

                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("23/09/2024 às 9h!")
                        }
                    },
                    color = White,
                    fontWeight = FontWeight(350),
                    letterSpacing = 1.sp,
                    fontSize = 16.sp,
                    style = TextStyle(
                        lineHeight = 24.sp
                    )
                )
            }
        }

        item {
            Text(
                text = "Pesquisas recentes",
                color = BLUE_PRIMARY,
                fontSize = 20.sp,
                letterSpacing = .5.sp,
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .padding(top = 25.dp)
            )
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth(.95f)
            ) {
                items(5) { // Exemplo com 3 itens; ajuste conforme necessário
                    CardImagemInfoBarbearia(
                        onClick = { navController.navigate("perfilBarbearia") },
                        nomeBarbearia = "Dom Bigode",
                        distancia = "200",
                        isOpen = true
                    )
                }
            }
        }

        item {
            Text(
                text = "Pode te interessar",
                color = BLUE_PRIMARY,
                fontSize = 20.sp,
                letterSpacing = .5.sp,
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .padding(top = 40.dp, bottom = 15.dp)
            )
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(150.dp)
                    .clip(RoundedCornerShape(15.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.capa_perfil_barbeiro),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color.Black.copy(alpha = 0.6f))
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .fillMaxHeight()
                        .fillMaxWidth(.9f)
                ) {
                    Text(
                        text = "Possui uma barbearia?",
                        color = White,
                        fontWeight = FontWeight(600),
                        letterSpacing = 1.sp,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    Text(
                        text = "Faça o cadastro dela e tenha uma infinidade de funções para a melhor gestão.",
                        color = White,
                        fontSize = 14.sp,
                        letterSpacing = .5.sp,
                        lineHeight = 24.sp,
                        modifier = Modifier.fillMaxWidth(.8f),
                        fontWeight = FontWeight(300)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clickable {
                                navController.navigate("cadastroBarbearia")
                            }
                            .size(40.dp)
                            .clip(RoundedCornerShape(7.dp))
                            .background(color = BLUE_PRIMARY)
                            .padding(5.dp)
                            .align(Alignment.BottomEnd),
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icone_setinha_svg),
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeUsuarioPreview() {
    val usuarios = usuarios()
    HomeUsuario(navController = rememberNavController(), usuarios.first())
}
