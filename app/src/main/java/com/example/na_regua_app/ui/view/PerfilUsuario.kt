package com.example.na_regua_app.ui.view

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.na_regua_app.R
import com.example.na_regua_app.data.model.Postagem
import com.example.na_regua_app.data.model.Usuario
import com.example.na_regua_app.ui.components.BotaoAjustavel
import com.example.na_regua_app.ui.components.BottomBarCustom
import com.example.na_regua_app.ui.components.PostCard
import com.example.na_regua_app.ui.components.TopBarCustom
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.viewmodel.PerfilUsuarioViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilUsuario(
    navController: NavController,
    perfilUsuarioViewModel: PerfilUsuarioViewModel = koinViewModel()
) {
    val usuario by perfilUsuarioViewModel.usuario.collectAsState()

    Scaffold(
        topBar = {
            TopBarCustom(navController, "Perfil",  true, true)
        },
        content = { paddingValues ->
            PerfilUsuarioContent(
                paddingValues = paddingValues,
                navController = navController,
                usuario = usuario
            )
        },
        bottomBar = {
            BottomBarCustom(navController)
        }
    )
}

@Composable
fun PerfilUsuarioContent(paddingValues: PaddingValues, navController: NavController, usuario: Usuario?) {

    if (usuario != null) {
        var nomeUsuario by remember { mutableStateOf(usuario.nome) }
        //Buscar no backend de onde virá os dados abaixo:
        var seguindo by remember { mutableStateOf(2) }
        var seguidores by remember { mutableStateOf(0) }
        var numeroDePostagens by remember { mutableStateOf(2) }
        //---
        var imgPerfil by remember { mutableStateOf(usuario.imgPerfil) }



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
                    AsyncImage(
                        model = imgPerfil,
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .size(90.dp)
                            .clip(RoundedCornerShape(50))
                            .border(3.dp, color = ORANGE_SECUNDARY, RoundedCornerShape(50))
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


                Espacamento(5.dp)


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
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                BotaoAjustavel(modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                    onClick = { /*TODO*/ },
                    textButton = "Editar perfil",
                    imagePainter = painterResource(R.drawable.edit_icon)
                )
            }

            Spacer(modifier = Modifier.size(15.dp))

            //Uma listagem de exemplo que será subtituida pela resposta da API no backend.
            val exemploPosts = listOf(
                Postagem(
                    fotoDePerfil = usuario.imgPerfil,
                    nomeDeUsuario = usuario.nome,
                    descricao = "Poxa pessoal, o aplicativo tá incrível!",
                    imagem = null
                ),
                Postagem(
                    fotoDePerfil = usuario.imgPerfil,
                    nomeDeUsuario = usuario.nome,
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
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val userName by remember { mutableStateOf(usuario.nome) }
                    val profilePic by remember { mutableStateOf(usuario.imgPerfil) }
                    val origin by remember { mutableStateOf("perfilUsuario") }

                    BotaoAjustavel(
                        modifier = Modifier.weight(1.5f),
                        onClick = { navController.navigate("chat/$userName/$profilePic/$origin") },
                        textButton = "Enviar mensagem",
                        imagePainter = painterResource(R.drawable.send_icon)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    BotaoAjustavel(
                        modifier = Modifier.weight(1.5f),
                        onClick = { navController.navigate("perfilBarbearia") },
                        textButton = "Visitar barbearia"
                    )
                }
            }
        }
    } else {
        // Mostre uma mensagem de carregamento ou erro...
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Carregando perfil...", color = Color.Gray)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PerfilUsuarioPreview() {
    val navController = rememberNavController()
    PerfilUsuario(navController = navController)
}

@Composable
fun PostList(posts: List<Postagem>) {
    Column {
        posts.forEach { post ->
            PostCard(post = post)
        }
    }
}