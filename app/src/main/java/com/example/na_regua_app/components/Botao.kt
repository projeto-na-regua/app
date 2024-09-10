package com.example.na_regua_app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.ui.theme.Typography
import com.example.na_regua_app.ui.theme.labelLargeOrange

@Composable
fun Botao(
    onClick: () -> Unit,
    textButton: String,
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = BLUE_PRIMARY
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(60.dp),
        shape = RoundedCornerShape(12.dp),
    ) {
        Text(
            text = textButton,
            style = labelLargeOrange,
            letterSpacing = 1.sp
        )
    }
}

@Composable
fun Botao(
    onClick: () -> Unit,
    textButton: String,
    imagePainter: Painter? = null // Adiciona um parâmetro para a imagem (opcional)
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = BLUE_PRIMARY
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(60.dp),
        shape = RoundedCornerShape(12.dp),
    ) {
        imagePainter?.let {
            Image(
                painter = it,
                contentDescription = null, // Pode ser nulo se a imagem for decorativa
                modifier = Modifier
                    .size(24.dp) // Tamanho da imagem
                    .padding(end = 8.dp) // Espaçamento entre a imagem e o texto
            )
        }

        Text(
            text = textButton,
            style = labelLargeOrange,
            letterSpacing = 1.sp
        )
    }
}

