package com.example.na_regua_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.na_regua_app.data.model.ChatPost

@Composable
fun MessageBubble(
    conteudo: String,
    imgPerfil: String?,
    tipoUsuario: String
) {

    val isCliente = tipoUsuario == "Cliente"

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (isCliente) Arrangement.End else Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        if (tipoUsuario == "Barbeiro") {
            // Imagem de perfil à esquerda para o barbeiro
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(
                        color = Color.Gray,
                        shape = CircleShape
                    )
            ) {
                AsyncImage(
                    model = imgPerfil,
                    contentDescription = "Imagem de perfil",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.size(8.dp)) // Espaço entre a imagem e o balão
        }

        val bubbleShape = CustomBubbleShape(isCliente)

        // Balão com ajuste de alinhamento
        Box(
            modifier = Modifier
                .wrapContentSize()
                .clip(bubbleShape)
                .background(if (isCliente) Color(0xFF0B2236) else Color(0xFFEEEFF1)) // Cor diferente para barbeiro
                .padding(bottom = 16.dp, top = 12.dp, start = 12.dp, end = 12.dp)
        ) {
            Text(
                text = conteudo,
                color = if (isCliente) Color.White else Color.Black, // Cor do texto
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )
        }

        if (tipoUsuario == "Cliente") {
            Spacer(modifier = Modifier.size(8.dp))

            // Imagem de perfil à direita para o cliente
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(
                        color = Color.Gray,
                        shape = CircleShape
                    )
            ) {
                AsyncImage(
                    model = imgPerfil,
                    contentDescription = "Imagem de perfil",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

class CustomBubbleShape(private val isCliente: Boolean) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: androidx.compose.ui.unit.LayoutDirection,
        density: androidx.compose.ui.unit.Density
    ): androidx.compose.ui.graphics.Outline {
        val path = Path().apply {
            if (isCliente) {
                // Cauda no lado direito para o cliente
                moveTo(30f, 0f)
                lineTo(size.width - 30f, 0f)
                quadraticBezierTo(size.width, 0f, size.width, 50f)
                lineTo(size.width, size.height - 40f)
                quadraticBezierTo(size.width, size.height, size.width - 30f, size.height)
                lineTo(size.width * 0.7f, size.height)
                lineTo(size.width * 0.9f, size.height + 30f)
                lineTo(size.width * 0.6f, size.height)
                lineTo(30f, size.height)
                quadraticBezierTo(0f, size.height, 0f, size.height - 30f)
                lineTo(0f, 30f)
                quadraticBezierTo(0f, 0f, 30f, 0f)
            } else {
                // Cauda no lado esquerdo para o barbeiro
                moveTo(30f, 0f)
                lineTo(size.width - 30f, 0f)
                quadraticBezierTo(size.width, 0f, size.width, 50f)
                lineTo(size.width, size.height - 30f)
                quadraticBezierTo(size.width, size.height, size.width - 30f, size.height)
                lineTo(size.width * 0.3f, size.height)
                lineTo(size.width * 0.1f, size.height + 30f)
                lineTo(size.width * 0.4f, size.height)
                lineTo(30f, size.height)
                quadraticBezierTo(0f, size.height, 0f, size.height - 30f)
                lineTo(0f, 30f)
                quadraticBezierTo(0f, 0f, 30f, 0f)
            }
        }
        return androidx.compose.ui.graphics.Outline.Generic(path)
    }
}
