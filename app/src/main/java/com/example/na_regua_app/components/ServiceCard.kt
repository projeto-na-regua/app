package com.example.na_regua_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.na_regua_app.classes.Servico
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.ui.theme.Typography
import com.example.na_regua_app.view.format

@Composable
fun ServiceCard(
    servico: Servico,
    isSelectable: Boolean,  // parâmetro para controle de seleção
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    val backgroundColor = if (isSelectable && isSelected) ORANGE_SECUNDARY else Color.Transparent

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(backgroundColor)
            .border(2.dp, Color.Gray, RoundedCornerShape(15.dp))
            .clickable(enabled = isSelectable && onClick != null) {
                onClick?.invoke()
            }  // Só permite clique se for selecionável e o callback estiver disponível
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(vertical = 8.dp, horizontal = 13.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 7.dp)
            ) {
                Text(
                    text = servico.tituloServico,
                    style = Typography.labelLarge,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = servico.descricao,
                    style = Typography.labelMedium,
                )
            }

            Box(
                modifier = Modifier
                    .height(80.dp)
                    .width(75.dp)
                    .background(BLUE_PRIMARY, CircleShape)
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text = "R$ ${servico.preco.format(2)}",
                    color = ORANGE_SECUNDARY,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun ServiceList(
    services: List<Servico>,
    isSelectable: Boolean,
    selectedService: Servico? = null,
    onServiceClick: ((Servico) -> Unit)? = null
) {
    Column (verticalArrangement = Arrangement.spacedBy(8.dp)) {
        services.forEach { service ->
            ServiceCard(
                servico = service,
                isSelectable = isSelectable,
                isSelected = isSelectable && selectedService == service,
                onClick = {
                    if (isSelectable && onServiceClick != null) {
                        onServiceClick(service)
                    }
                }
            )
        }
    }
}


