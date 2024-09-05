package com.example.na_regua_app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.na_regua_app.classes.Postagem
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import androidx.compose.material3.Text

@Composable
fun PostCard(post: Postagem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, end = 8.dp, bottom = 8.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = post.fotoDePerfil),
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
    }
}