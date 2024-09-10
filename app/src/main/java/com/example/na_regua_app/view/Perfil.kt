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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.R
import com.example.na_regua_app.classes.Postagem
import com.example.na_regua_app.classes.Servico
import com.example.na_regua_app.components.Botao
import com.example.na_regua_app.components.BottomBarCustom
import com.example.na_regua_app.components.PostCard
import com.example.na_regua_app.components.TopBarCustom
import com.example.na_regua_app.components.ServiceCard
import com.example.na_regua_app.components.ServiceList
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.ui.theme.Typography
import com.example.na_regua_app.ui.theme.WHITE_BACKGROUND
import com.example.na_regua_app.ui.theme.labelLargeOrange

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
    var numeroDePostagens by remember { mutableIntStateOf(2) }
    val selectedTab = remember { mutableStateOf("Perfil") }
    var localizacao by remember { mutableStateOf("Rua Piracicaba, 214 - SP") }
    var descricao by remember { mutableStateOf("Nossa equipe de barbeiros altamente qualificados é especializada em cortes de cabelo e barbas que atendem aos mais altos padrões de estilo. Utilizamos apenas produtos premium para garantir um acabamento impecável e um atendimento personalizado que reflete a verdadeira essência do seu estilo individual.") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(White),
            horizontalAlignment = Alignment.CenterHorizontally, // Aligns image and text in the center
            verticalArrangement = Arrangement.Center // Centers the content vertically
        ) {
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(50))  // Makes the image circular
                    .background(Color.Transparent)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.foto_perfil),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(50))  // Ensures the image is clipped as a circle
                        .background(Color.White),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(10.dp))  // Space between image and username

            Text(
                text = nomeUsuario,
                color = Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 13.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }


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

                Box(
                    modifier = Modifier
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

                    Row(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .align(Alignment.BottomCenter)
                            .padding(9.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Botao(onClick = { /*TODO*/ }, textButton = "Marcar horário")
                        Botao(onClick = { /*TODO*/ }, textButton = "Marcar horário")
                    }
                }

            }

            "Barbearia" -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                ) {
                    val scrollState = rememberScrollState()

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                            .padding(bottom = 8.dp)
                    ) {
                        Text(text = "Localização", style = Typography.titleMedium)

                        Espacamento(espaco = 8.dp)

                        Text(text = localizacao, style = Typography.labelMedium)

                        Espacamento(espaco = 8.dp)

                        Text(text = "Descrição", style = Typography.titleMedium)

                        Espacamento(espaco = 8.dp)

                        ExpandableText(descricao)

                        Espacamento(espaco = 8.dp)

                        BoxServicosPerfil()
                    }
                }
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
fun ExpandableText(description: String) {
    var isExpanded by remember { mutableStateOf(false) }
    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

    Column {
        // Descrição com limitação de linhas
        Text(
            text = description,
            style = Typography.labelMedium,
            maxLines = if (isExpanded) Int.MAX_VALUE else 3,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { textLayoutResult = it },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

        // Verifica se a descrição ocupa mais de 3 linhas e mostra o botão "ver mais" ou "ver menos"
        textLayoutResult?.let {
            if (it.hasVisualOverflow) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (isExpanded) "Ver menos" else "Ver mais",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .clickable { isExpanded = !isExpanded }
                        .padding(top = 4.dp) // Espaçamento extra para o botão
                )
            }
        }
    }
}


@Composable
fun BoxServicosPerfil() {

    val servicos = listOf(
        Servico(
            tituloServico = "Corte",
            descricao = "Corte simples de cabelo aaaa aaaaa aaaaa aaaa aaaaa aaaa",
            preco = 25.00
        ),
        Servico(
            tituloServico = "Corte + Escova",
            descricao = "Corte + escova",
            preco = 55.00
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Serviços",
                style = Typography.titleMedium
            )

            ServiceList(services = servicos, isSelectable = false)
        }
    }
}

@Composable
fun Espacamento(espaco: Dp){
    Spacer(modifier = Modifier.size(espaco))
}