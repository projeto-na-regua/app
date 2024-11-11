package com.example.na_regua_app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ChatCard(
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color.White
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = "imgPerfil"),
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .wrapContentHeight(Alignment.CenterVertically)
                    .size(48.dp)
                    .border(width = 1.dp, color = Color.Black, CircleShape)
                    .clip(CircleShape)
            )

            Column(
                modifier = Modifier
                    .padding(start = 6.dp)
            ) {
                Text(
                    fontSize = 15.sp,
                    text = "Fernando de Noronha"
                )

                Text(fontSize = 12.sp, text = "Olá como vai? Estou experimentando está...", color = Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatCardPreview(){
    ChatCard(modifier = Modifier)
}
