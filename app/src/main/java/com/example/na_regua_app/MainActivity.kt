package com.example.na_regua_app

import BuscaBarbearias
import Comunidade
import Configuracoes
import ConfiguracoesInformacoesPessoais
import ConfiguracoesSeuNegocio
import ExcluirConta
import ExcluirNegocio
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.data.di.appModule
import com.example.na_regua_app.data.model.BarbeariaArgs
import com.example.na_regua_app.data.model.usuarios
import com.example.na_regua_app.ui.theme.NareguaappTheme
import com.example.na_regua_app.ui.view.Adicionar
import com.example.na_regua_app.ui.view.AgendaUsuario
import com.example.na_regua_app.ui.view.Agendamento
import com.example.na_regua_app.ui.view.Cadastro
import com.example.na_regua_app.ui.view.CadastroBarbeariaEndereco
import com.example.na_regua_app.ui.view.CadastroBarbeariaFim
import com.example.na_regua_app.ui.view.CadastroBarbeariaFotoNome
import com.example.na_regua_app.ui.view.CadastroBarbeariaInicio
import com.example.na_regua_app.ui.view.CadastroFim
import com.example.na_regua_app.ui.view.CadastroFotoUsername
import com.example.na_regua_app.ui.view.CadastroInicio
import com.example.na_regua_app.ui.view.Chat
import com.example.na_regua_app.ui.view.Gestao
import com.example.na_regua_app.ui.view.Home
import com.example.na_regua_app.ui.view.HomeUsuario
import com.example.na_regua_app.ui.view.ListagemBarbearias
import com.example.na_regua_app.ui.view.Login
import com.example.na_regua_app.ui.view.Notificacoes
import com.example.na_regua_app.ui.view.PerfilBarbearia
import com.example.na_regua_app.ui.view.PerfilUsuario
import com.example.na_regua_app.ui.view.SplashScreen
import com.example.na_regua_app.ui.view.TelaInicial
import com.example.na_regua_app.ui.view.dashboard.Dashboard
import com.example.na_regua_app.utils.isUsuarioLogado
import com.example.na_regua_app.ui.view.Galeria
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@MainActivity)
            modules(listOf(appModule))
        }

        setContent {
            NareguaappTheme {
                val navController = rememberNavController()
                val isLogado = isUsuarioLogado(LocalContext.current)
                println(isLogado)
                val startDestination = if (isLogado) "telaInicial" else "login"

                NavHost(navController = navController, startDestination = startDestination) {

                    composable("home") {
                        Home(navController, usuarios().first())
                    }
                    composable("agendamento/{idBarbearia}/{isBarbeiro}") { backStackEntry ->
                        val idBarbearia = backStackEntry.arguments?.getString("idBarbearia")?.toIntOrNull() ?: -1
                        val isBarbeiro = backStackEntry.arguments?.getString("isBarbeiro")?.toBoolean() ?: true
                        if (idBarbearia != -1) {
                            Agendamento(navController, idBarbearia, isBarbeiro)
                        }
                    }
                    composable("buscarBarbearias") {
                        BuscaBarbearias(navController)
                    }
                    composable("listagemBarbearias/{servico}/{data}/{hora}") { backStackEntry ->
                        val servico = backStackEntry.arguments?.getString("servico")
                        val data = backStackEntry.arguments?.getString("data")
                        val hora = backStackEntry.arguments?.getString("hora")

                        // Criar um objeto BarbeariaArgs com os parâmetros
                        val args = BarbeariaArgs(
                            servico = Uri.decode(servico ?: ""),
                            data = Uri.decode(data ?: ""),
                            hora = Uri.decode(hora ?: "")
                        )

                        ListagemBarbearias(
                            navController = navController,
                            usuario = usuarios()[1],
                            args = args
                        )
                    }

                    composable("listagemBarbearias/{nomeBarbearia}") { backStackEntry ->
                        val nomeBarbearia = backStackEntry.arguments?.getString("nomeBarbearia")

                        // Criar um objeto BarbeariaArgs com os parâmetros
                        val args = BarbeariaArgs(
                            nomeBarbearia = nomeBarbearia
                        )

                        ListagemBarbearias(
                            navController = navController,
                            usuario = usuarios()[1],
                            args = args
                        )
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
                        Adicionar(navController, usuarios().first())
                    }
                    composable("perfilUsuario") {
                        PerfilUsuario(navController)
                    }
                    composable("perfilBarbearia/{isBarbeiro}/{idBarbearia}") { backStackEntry ->
                        val isBarbeiro = backStackEntry.arguments?.getString("isBarbeiro")?.toBoolean() ?: true
                        val idBarbearia = backStackEntry.arguments?.getString("idBarbearia")?.toIntOrNull() ?: -1
                        if (idBarbearia != -1) {
                            PerfilBarbearia(navController, isBarbeiro, idBarbearia)
                        }
                    }

                    composable("login") {
                        Login(navController)
                    }
                    composable("cadastro/{imagemUri}") { backStackEntry ->
                        val imagemUri = backStackEntry.arguments?.getString("imagemUri") ?: "Sem imagem"
                        Cadastro(navController, imagemUri)
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
                        ConfiguracoesInformacoesPessoais(navController, usuarios().first())
                    }
                    composable("settingsbusiness") {
                        ConfiguracoesSeuNegocio(navController, usuarios().first())
                    }
                    composable("deleteaccount") {
                        ExcluirConta(navController, usuarios()[1])
                    }
                    composable("deletebusiness") {
                        ExcluirNegocio(navController, usuarios().first())
                    }
                    composable("cadastroBarbeariaFotoUsername") {
                        CadastroBarbeariaFotoNome(navController)
                    }
                    composable("cadastroBarbeariaEndereco/{cpf}/{nomeDoNegocio}/{imagemSelecionadaBanner}/{imagemSelecionadaPerfil}") { backStackEntry ->
                        val cpf = backStackEntry.arguments?.getString("cpf") ?: "CPF Vazio"
                        val nomeDoNegocio = backStackEntry.arguments?.getString("nomeDoNegocio") ?: "Sem nome do negócio"
                        val imagemSelecionadaBanner = backStackEntry.arguments?.getString("imagemSelecionadaBanner") ?: "Sem foto de banner"
                        val imagemSelecionadaPerfil = backStackEntry.arguments?.getString("imagemSelecionadaPerfil") ?: "Sem foto de perfil"

                        CadastroBarbeariaEndereco(
                            navController = navController,
                            cpf = cpf,
                            nomeDoNegocio = nomeDoNegocio,
                            imagemPerfil = imagemSelecionadaPerfil,
                            imagemBanner = imagemSelecionadaBanner
                        )
                    }
                    composable("cadastroBarbeariaFim") {
                        CadastroBarbeariaFim(navController)
                    }
                    composable("cadastroFotoUsername") {
                        CadastroFotoUsername(navController)
                    }
                    composable("cadastroInicio") {
                        CadastroInicio(navController)
                    }
                    composable("cadastroFim") {
                        CadastroFim(navController)
                    }
                    composable("gestao") {
                        Gestao(navController, usuarios().first())
                    }
                    composable("galeria") {
                        Galeria(navController, usuarios()[1])
                    }
                    composable("agendaUsuarios") {
                        AgendaUsuario(navController, isFromDashboard = false)
                    }
                }
            }
        }
    }
}
