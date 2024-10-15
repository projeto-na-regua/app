package com.example.na_regua_app.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.ui.components.LogoImage
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("telaInicial") {
            popUpTo("splash_screen") { inclusive = true }
        }
    }

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(vertical = 50.dp, horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                LogoImage()
            }
        }
    )
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}
