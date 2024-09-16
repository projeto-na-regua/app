package com.example.na_regua_app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.na_regua_app.R
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY

@Composable
fun BarraPesquisar() {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF082031))
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.icone_lupa),
                contentDescription = "Ícone de lupa",
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Pesquisar barbearias",
                color = ORANGE_SECUNDARY,
                fontSize = 15.sp,
                letterSpacing = 1.sp,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.icone_filtro),
                contentDescription = "Ícone de filtro",
                modifier = Modifier.size(28.dp)
            )
        }
    }
}
