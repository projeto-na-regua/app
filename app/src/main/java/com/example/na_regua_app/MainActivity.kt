package com.example.na_regua_app

import Comunidade
import Configuracoes
import ConfiguracoesInformacoesPessoais
import ConfiguracoesSeuNegocio
import ExcluirConta
import ExcluirNegocio
import Home
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.ui.theme.NareguaappTheme
import com.example.na_regua_app.view.Adicionar
import com.example.na_regua_app.view.Agendamento
import com.example.na_regua_app.view.Cadastro
import com.example.na_regua_app.view.Login
import com.example.na_regua_app.view.Notificacoes
import com.example.na_regua_app.view.PerfilBarbearia
import com.example.na_regua_app.view.PerfilUsuario
import com.example.na_regua_app.view.SplashScreen
import com.example.na_regua_app.view.TelaInicial
import com.example.na_regua_app.view.dashboard.Dashboard
import com.example.na_regua_app.view.Chat
import com.example.na_regua_app.view.PerfilBarbearia
import com.example.na_regua_app.view.PerfilUsuario

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
                    composable("agendamento") {
                        Agendamento(navController)
                    }
                    composable("telaInicial"){
                        TelaInicial(navController)
                    }
                    composable("splashScreen"){
                        SplashScreen(navController)
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
                    composable("chat/{userName}/{profilePic}/{origin}") {backStackEntry ->
                        val userName = backStackEntry.arguments?.getString("userName") ?: "Nome Desconhecido"
                        val profilePic = backStackEntry.arguments?.getString("profilePic")?.toIntOrNull() ?: R.drawable.avatar_funcionario_default
                        val origin = backStackEntry.arguments?.getString("origin") ?: "Origem Desconhecida"

                        Chat(
                            navController = navController,
                            userName = userName,
                            profilePic = profilePic,
                            origin = origin
                        )
                    }
                    composable("adicionar") {
                        Adicionar(navController)
                    }
                    composable("perfilUsuario") {
                        PerfilUsuario(navController)
                    }
                    composable("perfilBarbearia"){
                        PerfilBarbearia(navController)
                    }
                    composable("login") {
                        Login(navController)
                    }
                    composable("cadastro") {
                        Cadastro(navController)
                    }
                    composable("settings") {
                        Configuracoes(navController)
                    }
                    composable("settingsprofile") {
                        ConfiguracoesInformacoesPessoais(navController)
                    }
                    composable("settingsbusiness") {
                        ConfiguracoesSeuNegocio(navController)
                    }
                    composable("deleteaccount") {
                        ExcluirConta(navController)
                    }
                    composable("deletebusiness") {
                        ExcluirNegocio(navController)
                    }
                }
            }
        }
    }
}
