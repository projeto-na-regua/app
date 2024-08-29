package com.example.na_regua_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.na_regua_app.ui.theme.FontProvider
import com.example.na_regua_app.ui.theme.WHITE_BACKGROUND


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarCustom(
    navController: NavController,
    titlePage: String,
    showIconNotification: Boolean){
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .background(Color.White)
            .drawBehind {
                val strokeWidth = 2.dp.toPx()
                val y = size.height - strokeWidth / 2
                drawLine(
                    color = WHITE_BACKGROUND,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = strokeWidth
                )

                drawRect(
                    color = Color.Black.copy(alpha = 0.2f),
                    size = Size(size.width, 4.dp.toPx()),
                    topLeft = Offset(0f, size.height - 4.dp.toPx())
                )
            }
            .padding(bottom = 2.dp),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    Text(
                        text = titlePage,
                        fontFamily = FontProvider.PlusJakartaSans,
                        color = Color.White)
                }

                if(showIconNotification){
                    IconButton(onClick = {
                        navController.navigate("notificacoes")
                    }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notificações"
                        )
                    }
                }
            }
        },
    )
}