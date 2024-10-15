package com.example.na_regua_app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.na_regua_app.data.model.Postagem
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.na_regua_app.R

@Composable
fun PostCard(post: Postagem) {
    var showDialog by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, end = 8.dp, bottom = 8.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .padding(8.dp)
            .clickable { showDialog = true }
    ) {
        AsyncImage(
            model = post.fotoDePerfil,
            contentDescription = "Foto do Barbeiro",
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(50))
                .border(2.dp, ORANGE_SECUNDARY, RoundedCornerShape(50)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = post.nomeDeUsuario,
                color = BLUE_PRIMARY,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = post.descricao,
                color = Color.Black,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            post.imagem?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = "Imagem do Post",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(11)),
                    contentScale = ContentScale.Crop
                )
            }
        }

        if (showDialog) {
            PostDetailsDialog(post = post, onDismiss = { showDialog = false })
        }
    }
}

@Composable
fun PostDetailsDialog(
    post: Postagem,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Detalhes da Postagem",
                    fontWeight = FontWeight.Bold,
                    color = BLUE_PRIMARY,
                    fontSize = 20.sp
                )
            }
        },
        text = {
            Column {
                // Exibe a foto de perfil e o nome de usuário em uma Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = post.fotoDePerfil,
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(50))
                            .border(2.dp, ORANGE_SECUNDARY, RoundedCornerShape(50)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = post.nomeDeUsuario,
                        fontWeight = FontWeight.Bold,
                        color = BLUE_PRIMARY,
                        fontSize = 16.sp
                    )
                }

                // Exibe a descrição do post
                Text(
                    text = post.descricao,
                    fontSize = 14.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Verifica se o post possui uma imagem, se sim, exibe-a
                post.imagem?.let {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = "Imagem do Post",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(15)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        },
        confirmButton = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp),
                contentAlignment = Alignment.Center
            ) {
                Botao(onClick = { onDismiss() }, textButton = "Fechar")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewPostDetailsDialog() {
    val exemploPost = Postagem(
        fotoDePerfil = R.drawable.foto_perfil.toString(),
        nomeDeUsuario = "@barbeiro_ofc",
        descricao = "Primeira postagem na comunidade do Na Régua, sigam meu " +
                "perfil e deem uma olhada nos serviços da minha barbearia.",
        imagem = R.drawable.imagem_post
    )

    PostDetailsDialog(post = exemploPost, onDismiss = {})
}
