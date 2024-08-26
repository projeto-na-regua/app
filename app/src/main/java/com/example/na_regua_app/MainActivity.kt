package com.example.na_regua_app

import Home
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.ui.theme.NareguaappTheme
import com.example.na_regua_app.view.Notificacoes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NareguaappTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        Home(navController)
                    }
                    composable("notificacoes") {
                        Notificacoes(navController)
                    }
                    composable("home") {
                        Home(navController)
                    }
                    composable("home") {
                        Home(navController)
                    }
                    composable("home") {
                        Home(navController)
                    }
                    composable("home") {
                        Home(navController)
                    }
                    composable("home") {
                        Home(navController)
                    }
                }
            }
        }
    }
}
