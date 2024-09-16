package com.example.na_regua_app.view

import android.content.ClipData.Item
import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.R
import com.example.na_regua_app.components.BarraPesquisar
import com.example.na_regua_app.components.Botao
import com.example.na_regua_app.components.BotaoSpan
import com.example.na_regua_app.components.BottomBarCustom
import com.example.na_regua_app.components.CardImagemInfoBarbearia
import com.example.na_regua_app.components.Input
import com.example.na_regua_app.components.InputCadastro
import com.example.na_regua_app.components.LogoImage
import com.example.na_regua_app.components.SelecaoFuncionarios
import com.example.na_regua_app.components.TopBarCustom
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun HomeUsuario(
    navController: NavController
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
                paddingValues = paddingValues
            )
        },
        bottomBar = {
            BottomBarCustom(navController)
        }
    )
}

@Composable
fun HomeUsuarioContent(paddingValues: PaddingValues) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp)
    ) {
        BarraPesquisar()
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

                    // Estilo para "Dom Bigode" (cor diferente)
                    withStyle(style = SpanStyle(color = ORANGE_SECUNDARY, fontWeight = FontWeight.Bold)) { // Cor laranja, por exemplo
                        append("Dom Bigode")
                    }

                    append(". \nInformações do Agendamento: ")

                    // Estilo para "Corte + Escova" (negrito)
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Corte + Escova")
                    }

                    append(" no dia ")

                    // Estilo para "23/09/2024 às 9h!" (negrito)
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("23/09/2024 às 9h!")
                    }
                },
                color = White,
                fontWeight = FontWeight(350),
                letterSpacing = 1.sp,
                fontSize = 16.sp,
                style = TextStyle(
                    lineHeight = 24.sp // Define o espaçamento entre as linhas
                )
            )
        }

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

        LazyRow (
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth(.9f)
        ){
            item {
                CardImagemInfoBarbearia(
                    onClick = { /*TODO*/ },
                    nomeBarbearia = "Dom bigode",
                    distancia = "200",
                    isOpen = true
                )
                CardImagemInfoBarbearia(
                    onClick = { /*TODO*/ },
                    nomeBarbearia = "Dom bigode",
                    distancia = "200",
                    isOpen = true
                )
                CardImagemInfoBarbearia(
                    onClick = { /*TODO*/ },
                    nomeBarbearia = "Dom bigode",
                    distancia = "200",
                    isOpen = true
                )
            }
        }

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
            Column (
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(start = 15.dp)
                    .fillMaxHeight()
                    .fillMaxWidth(.9f)
            ){
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
                    .padding(16.dp) // Espaçamento do Box interno
            ) {
                // Box com o ícone posicionado no BottomEnd
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .background(color = BLUE_PRIMARY)
                        .padding(5.dp)
                        .align(Alignment.BottomEnd),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icone_setinha),
                        contentDescription = ""
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun HomeUsuarioPreview() {
    HomeUsuario(navController = rememberNavController())
}