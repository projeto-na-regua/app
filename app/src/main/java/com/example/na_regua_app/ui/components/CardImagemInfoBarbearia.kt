package com.example.na_regua_app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.na_regua_app.R
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.ui.theme.labelLargeOrange

@Composable
fun CardImagemInfoBarbearia(
    onClick: () -> Unit,
    nomeBarbearia: String,
    imagem: String?
) {
    Column(
        modifier = Modifier.width(130.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color(0xFFF0F0F0))
            .padding(15.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .padding()
                .size(80.dp)
                .clip(CircleShape) // Certifica-se de que o Box seja recortado como um círculo
                .background(Color.Transparent)
                .border(1.dp, color = Color(0xFF9E9E9E), CircleShape)
        ) {
            if (imagem != null) {
                Image(
                    painter = rememberAsyncImagePainter(model = imagem),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.perfil_barbearia),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        Text(
            modifier = Modifier.padding(top = 10.dp)
                .fillMaxWidth(),
            text = nomeBarbearia,
            fontSize = 16.sp,
            color = BLUE_PRIMARY,
            fontWeight = FontWeight(600),
            letterSpacing = .5.sp,
            textAlign = TextAlign.Center
        )
    }
}
