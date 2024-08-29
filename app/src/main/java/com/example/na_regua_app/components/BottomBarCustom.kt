package com.example.na_regua_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.na_regua_app.R
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.ui.theme.WHITE_BACKGROUND


@Composable
fun BottomBarCustom(navController: NavController){

    BottomAppBar(
        containerColor = Color.White,
        contentColor = ORANGE_SECUNDARY,
        modifier = Modifier.fillMaxWidth() // Cor do fundo do Box
            .drawBehind {
                val strokeWidth = 2.dp.toPx()
                val shadowHeight = 4.dp.toPx()

                // Desenhar a sombra
                drawRect(
                    color = Color.Black.copy(alpha = 0.2f),
                    size = Size(size.width, shadowHeight),
                    topLeft = Offset(0f, -shadowHeight) // Posicionar a sombra acima do Box
                )

                // Desenhar a borda superior
                drawLine(
                    color = WHITE_BACKGROUND,
                    start = Offset(0f, -strokeWidth / 2),
                    end = Offset(size.width, -strokeWidth / 2),
                    strokeWidth = strokeWidth
                )
            }
            .padding(top = 2.dp),
        actions = {
            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(id = R.drawable.home_icon),
                contentDescription = "Home",
                modifier = Modifier
                    .size(54.dp)
                    .padding(12.dp)
                    .clickable {
                        navController.navigate("home")
                    }
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(id = R.drawable.dash_icon),
                contentDescription = "Dashboard",
                modifier = Modifier
                    .size(54.dp)
                    .padding(12.dp)
                    .clickable {
                        navController.navigate("dashboard")
                    }
            )

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .size(54.dp)
                    .clip(RoundedCornerShape(28))
                    .background(BLUE_PRIMARY)
                    .clickable {
                        navController.navigate("adicionar")
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.plus_icon),
                    contentDescription = "Adicionar",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(12.dp)
                        .size(48.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(id = R.drawable.comunnity_icon),
                contentDescription = "Comunidade",
                modifier = Modifier
                    .size(54.dp)
                    .padding(12.dp)
                    .clickable {
                        navController.navigate("comunidade")
                    }
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(id = R.drawable.person_icon),
                contentDescription = "Perfil",
                modifier = Modifier
                    .size(54.dp)
                    .padding(12.dp)
                    .clickable{
                        navController.navigate("perfil")
                    }
            )


            Spacer(modifier = Modifier.weight(1f))
        },
    )
}

