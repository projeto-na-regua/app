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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.na_regua_app.R
import com.example.na_regua_app.classes.Postagem
import com.example.na_regua_app.classes.Servico
import com.example.na_regua_app.components.BotaoAjustavel
import com.example.na_regua_app.components.BottomBarCustom
import com.example.na_regua_app.components.PostCard
import com.example.na_regua_app.components.TopBarCustom
import com.example.na_regua_app.components.ServiceList
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.ui.theme.Typography


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilBarbeiro(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopBarCustom(navController, "Perfil",  true)
        },
        content = { paddingValues ->
            PerfilBarbeiroContent(
                paddingValues = paddingValues
            )
        },
        bottomBar = {
            BottomBarCustom(navController)
        }
    )
}


@Composable
fun PerfilBarbeiroContent(paddingValues: PaddingValues) {

    var nomeBarbearia by remember { mutableStateOf("@barbearia_top_de_linha") }
    var localizacao by remember { mutableStateOf("Rua Piracicaba, 214 - SP") }
    var descricao by remember { mutableStateOf("Nossa equipe de barbeiros altamente qualificados é especializada em cortes de cabelo e barbas que atendem aos mais altos padrões de estilo.") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color.White)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
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
                    modifier = Modifier.fillMaxWidth(1f).height(120.dp).border(1.dp, color = BLUE_PRIMARY),
                    contentScale = ContentScale.Crop
                )
            }


                Box(
                    modifier = Modifier
                        .offset(y = 90.dp)
                        .padding(start = 12.dp)
                ) {

                    Row(modifier = Modifier.fillMaxWidth()){

                    Box(
                        modifier = Modifier
                            .size(90.dp)
                            .clip(RoundedCornerShape(50))
                            .border(2.dp, color = ORANGE_SECUNDARY, shape = RoundedCornerShape(50))
                            .background(Color.White)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.foto_perfil),
                            contentDescription = "Foto de perfil",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(50))
                                .background(Color.White),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.weight(0.6f))

                    BotaoAjustavel(modifier = Modifier
                        .weight(0.8f).padding(end = 12.dp).align(Alignment.Bottom),
                        onClick = { /*TODO*/ }, textButton = "Editar perfil", imagePainter = painterResource(R.drawable.edit_icon))
                }
            }
        }


        Espacamento(70.dp)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            item {
                Text(
                    text = nomeBarbearia,
                    color = BLUE_PRIMARY,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    fontSize = 11.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                )
                Espacamento(espaco = 8.dp)
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.location_icon),
                        contentDescription = "Ícone Localização",
                        modifier = Modifier.padding(end = 4.dp)
                    )

                    Text(
                        text = localizacao,
                        style = Typography.labelSmall,
                        modifier = Modifier.padding(end = 8.dp)
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.start_icon),
                        contentDescription = "Ícone Estrela",
                        modifier = Modifier.padding(end = 4.dp)
                    )

                    Text(
                        text = "4,5",
                        style = Typography.labelSmall
                    )
                }

                Espacamento(espaco = 8.dp)
            }

            item {
                Text(text = "Descrição", style = Typography.titleMedium)
                Espacamento(espaco = 8.dp)
            }

            item {
                Text(text = descricao, style = Typography.labelSmall)
                Espacamento(espaco = 8.dp)
            }

            item {
                BoxServicosPerfil()
            }
        }

    }
}



@Preview(showBackground = true)
@Composable
fun PerfilBarbeiroPreview() {
    val navController = rememberNavController()
    PerfilBarbeiro(navController = navController)
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
