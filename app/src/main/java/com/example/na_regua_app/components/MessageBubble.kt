package com.example.na_regua_app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.na_regua_app.R
import com.example.na_regua_app.ui.theme.WHITE_BACKGROUND

@Composable
fun MessageBubble(text: String, imagePainter: Int, isCurrentUser: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (isCurrentUser) Arrangement.End else Arrangement.Start, // Alinha à esquerda ou direita
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        if (!isCurrentUser) {
            // Círculo à esquerda para o outro usuário
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(
                        color = Color.Gray,
                        shape = CircleShape
                    )
            ) {
                Image(
                    painter = painterResource(id = imagePainter),
                    contentDescription = "Ícone de usuário",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.size(8.dp)) // Espaço entre a imagem e o balão
        }

        val bubbleShape = CustomBubbleShape(isCurrentUser)

        // Balão com ajuste de alinhamento
        Box(
            modifier = Modifier
                .wrapContentSize()
                .clip(bubbleShape)
                .background(if (isCurrentUser) Color(0xFF0B2236) else Color(0xFFEEEFF1)) // Cor diferente para outro usuário
                .padding(bottom = 16.dp, top = 12.dp, start = 12.dp, end = 12.dp)
        ) {
            Text(
                text = text,
                color = if (isCurrentUser) Color.White else Color.Black, // Cor do texto
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )
        }

        if (isCurrentUser) {
            Spacer(modifier = Modifier.size(8.dp))

            // Círculo à direita para o usuário atual
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(
                        color = Color.Gray,
                        shape = CircleShape
                    )
            ) {
                Image(
                    painter = painterResource(id = imagePainter), // Imagem recebida como parâmetro
                    contentDescription = "Ícone de usuário",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}


class CustomBubbleShape(private val isCurrentUser: Boolean) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: androidx.compose.ui.unit.LayoutDirection,
        density: androidx.compose.ui.unit.Density
    ): androidx.compose.ui.graphics.Outline {
        val path = Path().apply {
            if (isCurrentUser) {
                // Cauda no lado direito para o currentUser
                moveTo(30f, 0f)

                // Linha até o canto superior direito
                lineTo(size.width - 30f, 0f)

                // Canto superior direito arredondado
                quadraticBezierTo(size.width, 0f, size.width, 50f)

                // Linha até o canto inferior direito
                lineTo(size.width, size.height - 40f)

                // Canto inferior direito arredondado
                quadraticBezierTo(size.width, size.height, size.width - 30f, size.height)

                // Desenhar a cauda mais acentuada no lado direito
                lineTo(size.width * 0.7f, size.height) // Ponto de início da cauda
                lineTo(size.width * 0.9f, size.height + 30f) // Ponta acentuada da cauda
                lineTo(size.width * 0.6f, size.height) // Volta para o balão

                // Linha até o canto inferior esquerdo
                lineTo(30f, size.height)

                // Canto inferior esquerdo arredondado
                quadraticBezierTo(0f, size.height, 0f, size.height - 30f)

                // Linha até o canto superior esquerdo
                lineTo(0f, 30f)

                // Canto superior esquerdo arredondado
                quadraticBezierTo(0f, 0f, 30f, 0f)
            } else {
                // Cauda no lado esquerdo para o outro usuário
                moveTo(30f, 0f)

                // Linha até o canto superior direito
                lineTo(size.width - 30f, 0f)

                // Canto superior direito arredondado
                quadraticBezierTo(size.width, 0f, size.width, 50f)

                // Linha até o canto inferior direito
                lineTo(size.width, size.height - 30f)

                // Canto inferior direito arredondado
                quadraticBezierTo(size.width, size.height, size.width - 30f, size.height)

                // Desenhar a cauda mais acentuada no lado esquerdo
                lineTo(size.width * 0.3f, size.height) // Ponto de início da cauda
                lineTo(size.width * 0.1f, size.height + 30f) // Ponta acentuada da cauda
                lineTo(size.width * 0.4f, size.height) // Volta para o balão

                // Linha até o canto inferior esquerdo
                lineTo(30f, size.height)

                // Canto inferior esquerdo arredondado
                quadraticBezierTo(0f, size.height, 0f, size.height - 30f)

                // Linha até o canto superior esquerdo
                lineTo(0f, 30f)

                // Canto superior esquerdo arredondado
                quadraticBezierTo(0f, 0f, 30f, 0f)
            }
        }

        return androidx.compose.ui.graphics.Outline.Generic(path)
    }
}


@Preview(showBackground = true)
@Composable
fun MessageBubblePreview() {
    Column{
        MessageBubble("Olá, tudo bem?", R.drawable.foto_perfil, isCurrentUser = true)
        MessageBubble("Estou bem sim!", R.drawable.barbeiro1, isCurrentUser = false)
    }
}