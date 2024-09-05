package com.example.na_regua_app.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.R
import com.example.na_regua_app.classes.Postagem
import com.example.na_regua_app.components.Botao
import com.example.na_regua_app.components.BottomBarCustom
import com.example.na_regua_app.components.PostCard
import com.example.na_regua_app.components.TopBarCustom
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.ui.theme.WHITE_BACKGROUND

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Perfil(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopBarCustom(navController, "Perfil",  true)
        },
        content = { paddingValues ->
            PerfilContent(
                paddingValues = paddingValues
            )
        },
        bottomBar = {
            BottomBarCustom(navController)
        }
    )
}

@Composable
fun PerfilContent(paddingValues: PaddingValues){

    var nomeUsuario by remember { mutableStateOf("@barbeiro_ofc") }
    var seguindo by remember { mutableIntStateOf(2) }
    var seguidores by remember { mutableIntStateOf(0) }
    var numeroDePostagens by remember { mutableStateOf(2) }
    val selectedTab = remember { mutableStateOf("Perfil") }
    var possuiAgendamentos by remember { mutableStateOf(true) }
    var saldo by remember { mutableDoubleStateOf(214.00) }
    var visitantes by remember { mutableIntStateOf(50) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color.White)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(White)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .drawBehind {
                        val strokeWidth = 10.dp.toPx()
                        val y = size.height - strokeWidth / 2
                        drawLine(
                            color = ORANGE_SECUNDARY,
                            start = Offset(0f, y),
                            end = Offset(size.width, y),
                            strokeWidth = strokeWidth
                        )
                    }
            ) {

                Image(
                    painter = painterResource(id = R.drawable.capa_perfil_barbeiro),
                    contentDescription = "Foto de capa do barbeiro",
                    modifier = Modifier.fillMaxWidth(1f),
                    contentScale = ContentScale.Crop
                )
            }


            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = 60.dp)
                    .size(130.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color.Transparent)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(50))
                        .background(Color.White)

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.foto_perfil),
                        contentDescription = "Foto de perfil",
                        modifier = Modifier.fillMaxWidth(1f),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }


        Espacamento(70.dp)


        Text(
            text = "$nomeUsuario",
            color = Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 13.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Espacamento(13.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {


            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(end = 14.dp)
            ) {
                Text(
                    text = "$seguindo",
                    color = Black,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .alignByBaseline()
                        .padding(end = 6.dp)
                        .offset(y = (4).dp)
                )

                Text(
                    text = "seguindo",
                    color = Black,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                )
            }


            Espacamento(30.dp)


            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "$seguidores",
                    color = Black,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .alignByBaseline()
                        .padding(end = 6.dp)
                        .offset(y = (4).dp)
                )

                Text(
                    text = "seguidores",
                    color = Black,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                )
            }
        }

        Espacamento(20.dp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavItem(
                text = "Perfil",
                isSelected = selectedTab.value == "Perfil",
                onClick = { selectedTab.value = "Perfil" }
            )
            NavItem(
                text = "Barbearia",
                isSelected = selectedTab.value == "Barbearia",
                onClick = { selectedTab.value = "Barbearia" }
            )
        }

        Espacamento(15.dp)

        when (selectedTab.value) {
            "Perfil" -> {
                //Uma listagem de exemplo que será subtituida pela resposta da API no backend.
                val exemploPosts = listOf(
                    Postagem(
                        fotoDePerfil = R.drawable.foto_perfil,
                        nomeDeUsuario = "@barbeiro_ofc",
                        descricao = "Poxa pessoal, o aplicativo tá incrível!",
                        imagem = null
                    ),
                    Postagem(
                        fotoDePerfil = R.drawable.foto_perfil,
                        nomeDeUsuario = "@barbeiro_ofc",
                        descricao = "Primeira postagem na comunidade do Na Régua, sigam meu " +
                                    "perfil e deem uma olhada nos serviços da minha barbearia.",
                        imagem = R.drawable.imagem_post
                    )
                )

                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 56.dp)
                    ) {
                        Text(
                            text = "$numeroDePostagens postagens",
                            color = BLUE_PRIMARY,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .verticalScroll(rememberScrollState())
                        ) {
                            PostList(posts = exemploPosts)
                        }

                        Spacer(modifier = Modifier.height(18.dp))
                    }

                    Box(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .align(Alignment.BottomCenter)
                            .padding(9.dp)
                    ) {
                        Botao(onClick = { /*TODO*/ }, textButton = "Marcar horário")
                    }
                }

            }
            "Barbearia" -> {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PerfilPreview() {
    val navController = rememberNavController()
    Perfil(navController = navController)
}

@Composable
fun NavItem(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 35.dp)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            color = if (isSelected) ORANGE_SECUNDARY else LightGray,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            fontSize = 13.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            Modifier
                .height(2.dp)
                .width(98.dp)
                .background(if (isSelected) ORANGE_SECUNDARY else Color.Transparent)
        )
    }
}

@Composable
fun PostList(posts: List<Postagem>) {
    Column {
        posts.forEach { post ->
            PostCard(post = post)
        }
    }
}

@Composable
fun Espacamento(espaco: Dp){
    Spacer(modifier = Modifier.size(espaco))
}