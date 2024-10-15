package com.example.na_regua_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.FontProvider
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.ui.theme.Typography
import com.example.na_regua_app.ui.theme.WHITE_BACKGROUND


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarCustom(
    navController: NavController,
    titlePage: String,
    showIconNotification: Boolean,
    showIconSettings: Boolean = false,
    showBackButton: Boolean = false
) {

    var existNotification by remember {mutableStateOf(true)}

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

            }
            .padding(bottom = 2.dp),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(showBackButton) {
                    Box {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Voltar",
                            tint = Color.Black,
                            modifier = Modifier.clickable(onClick = {
                                navController.popBackStack()
                            })
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))
                }

                Box(modifier = Modifier.weight(1f)) {
                    Text(
                        text = titlePage,
                        style = Typography.titleLarge
                    )
                }

                if(showIconNotification){
                    Box (modifier = Modifier.padding(10.dp)){
                        IconButton(onClick = {
                            navController.navigate("notificacoes")
                        }) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Notificações",
                                tint = Color.Black
                            )
                        }

                        if (existNotification) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .background(ORANGE_SECUNDARY, shape = CircleShape)
                                    .align(Alignment.TopEnd)
                            )
                        }
                    }
                }

                if(showIconSettings) {
                    Box {
                        IconButton(onClick = {
                            navController.navigate("settings")
                        }) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Settings",
                                tint = Color.Black
                            )
                        }
                    }
                }
            }
        },
    )
}