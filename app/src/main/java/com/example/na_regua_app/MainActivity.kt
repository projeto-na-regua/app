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
import com.example.na_regua_app.classes.usuarios
import com.example.na_regua_app.ui.theme.NareguaappTheme
import com.example.na_regua_app.view.Adicionar
import com.example.na_regua_app.view.Agendamento
import com.example.na_regua_app.view.Cadastro
import com.example.na_regua_app.view.CadastroBarbeariaEndereco
import com.example.na_regua_app.view.CadastroBarbeariaFim
import com.example.na_regua_app.view.CadastroBarbeariaFotoNome
import com.example.na_regua_app.view.CadastroBarbeariaInicio
import com.example.na_regua_app.view.CadastroFim
import com.example.na_regua_app.view.CadastroFotoUsername
import com.example.na_regua_app.view.CadastroInicio
import com.example.na_regua_app.view.Chat
import com.example.na_regua_app.view.Gestao
import com.example.na_regua_app.view.HomeUsuario
import com.example.na_regua_app.view.Login
import com.example.na_regua_app.view.Notificacoes
import com.example.na_regua_app.view.PerfilBarbearia
import com.example.na_regua_app.view.PerfilUsuario
import com.example.na_regua_app.view.SplashScreen
import com.example.na_regua_app.view.TelaInicial
import com.example.na_regua_app.view.dashboard.Dashboard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NareguaappTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "telaInicial") {
                    composable("home") {
                        Home(navController, usuarios().first())
                    }
                    composable("agendamento") {
                        Agendamento(navController, usuarios()[1])
                    }
                    composable("telaInicial") {
                        TelaInicial(navController)
                    }
                    composable("splashScreen") {
                        SplashScreen(navController)
                    }
                    composable("notificacoes") {
                        Notificacoes(navController, usuarios().first())
                    }
                    composable("dashboard") {
                        Dashboard(navController, usuarios().first())
                    }
                    composable("comunidade") {
                        Comunidade(navController, usuarios().first())
                    }
                    composable("chat/{userName}/{profilePic}/{origin}") { backStackEntry ->
                        val userName =
                            backStackEntry.arguments?.getString("userName") ?: "Nome Desconhecido"
                        val profilePic =
                            backStackEntry.arguments?.getString("profilePic")?.toIntOrNull()
                                ?: R.drawable.avatar_funcionario_default
                        val origin =
                            backStackEntry.arguments?.getString("origin") ?: "Origem Desconhecida"

                        Chat(
                            navController = navController,
                            userName = userName,
                            profilePic = profilePic,
                            origin = origin
                        )
                    }
                    composable("adicionar") {
                        Adicionar(navController, usuarios().first())
                    }
                    composable("perfilUsuario") {
                        PerfilUsuario(navController, usuarios()[1])
                    }
                    composable("perfilBarbearia") {
                        PerfilBarbearia(navController, usuarios().first())
                    }
                    composable("login") {
                        Login(navController)
                    }
                    composable("cadastro") {
                        Cadastro(navController)
                    }
                    composable("cadastroBarbearia") {
                        CadastroBarbeariaInicio(navController)
                    }
                    composable("homeUsuario") {
                        HomeUsuario(navController, usuarios()[1])
                    }
                    composable("settings") {
                        Configuracoes(navController, usuarios()[1])
                    }
                    composable("settingsprofile") {
                        ConfiguracoesInformacoesPessoais(navController, usuarios()[1])
                    }
                    composable("settingsbusiness") {
                        ConfiguracoesSeuNegocio(navController,usuarios().first())
                    }
                    composable("deleteaccount") {
                        ExcluirConta(navController, usuarios()[1])
                    }
                    composable("deletebusiness") {
                        ExcluirNegocio(navController,usuarios().first())
                    }
                    composable("cadastroBarbeariaFotoUsername"){
                        CadastroBarbeariaFotoNome(navController)
                    }
                    composable("cadastroBarbeariaEndereco"){
                        CadastroBarbeariaEndereco(navController)
                    }
                    composable("cadastroBarbeariaFim"){
                        CadastroBarbeariaFim(navController)
                    }
                    composable("cadastroFotoUsername"){
                        CadastroFotoUsername(navController)
                    }
                    composable("cadastroInicio"){
                        CadastroInicio(navController)
                    }
                    composable("cadastroFim"){
                        CadastroFim(navController)
                    }
                    composable("gestao"){
                        Gestao(navController, usuarios().first())
                    }
                    }
                }
            }
        }
    }

