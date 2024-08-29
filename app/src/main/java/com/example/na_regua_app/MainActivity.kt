package com.example.na_regua_app

import Comunidade
import Home
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.ui.theme.NareguaappTheme
import com.example.na_regua_app.view.Adicionar
import com.example.na_regua_app.view.Cadastro
import com.example.na_regua_app.view.Dashboard
import com.example.na_regua_app.view.Login
import com.example.na_regua_app.view.Notificacoes
import com.example.na_regua_app.view.Perfil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NareguaappTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("home") {
                        Home(navController)
                    }
                    composable("notificacoes") {
                        Notificacoes(navController)
                    }
                    composable("dashboard") {
                        Dashboard(navController)
                    }
                    composable("comunidade") {
                        Comunidade(navController)
                    }
                    composable("adicionar") {
                        Adicionar(navController)
                    }
                    composable("perfil") {
                        Perfil(navController)
                    }
                    composable("login") {
                        Login(navController)
                    }
                    composable("cadastro") {
                        Cadastro(navController)
                    }
                }
            }
        }
    }
}
