package com.example.na_regua_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.na_regua_app.classes.Funcionario
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.ui.theme.Typography
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.WHITE_BACKGROUND

@Composable
fun SelecaoFuncionarios(
    funcionario: Funcionario,
    isSelectable: Boolean,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
    exibirInformacoes: Boolean = false
) {
    val borderColor = if (isSelectable && isSelected) ORANGE_SECUNDARY else Color.Gray

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Box para a imagem circular do funcionário
        Box(
            modifier = Modifier
                .size(85.dp)
                .clip(CircleShape)
                .background(Color.Transparent)
                .border(3.dp, borderColor, CircleShape)
                .clickable(enabled = isSelectable) {
                    if (isSelectable) {
                        onClick.invoke()
                    }
                }
        ) {
            if (funcionario.imgPerfil != null) {
                Image(
                    painter = painterResource(id = funcionario.imgPerfil),
                    contentDescription = "Imagem do Funcionário",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(85.dp)
                        .clip(CircleShape)
                )
            } else {
                Text(
                    text = "Foto Barbeiro",
                    style = Typography.labelMedium,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        // Exibe nome e dtype se exibirInformacoes for true
        if (exibirInformacoes) {
            // Nome do funcionário
            Text(
                text = funcionario.nome,
                style = Typography.titleSmall,
                color = BLUE_PRIMARY,
                modifier = Modifier.padding(top = 4.dp)
            )

            // dtype indicando se é Barbeiro ou Admin
            Text(
                text = funcionario.dtype,
                style = Typography.titleSmall,
                color = WHITE_BACKGROUND,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}