package com.example.na_regua_app.ui.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.ui.components.BotaoSpan
import com.example.na_regua_app.ui.components.Input
import com.example.na_regua_app.ui.components.LogoImage
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.utils.obterUsuarioDtype
import com.example.na_regua_app.viewmodel.LoginViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Login(
    navController: NavController,
    loginViewModel: LoginViewModel = koinViewModel()
) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    val context = LocalContext.current
    val userDtype by obterUsuarioDtype(context).collectAsState(initial = null)

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(vertical = 50.dp, horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                LogoImage()
                Text(
                    text = "Faça seu login",
                    fontSize = 24.sp,
                    color = BLUE_PRIMARY,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 40.dp),
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Acesse sua conta e venha ficar na régua conosco!",
                    fontSize = 12.sp,
                    color = BLUE_PRIMARY,
                    modifier = Modifier.padding(top = 12.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 1.sp,
                )
                Column(
                    modifier = Modifier
                        .padding(top = 18.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Input(
                        value = email,
                        onValueChange = { novoValor -> email = novoValor },
                        label = { Text("E-mail") }
                    )
                    Input(
                        value = senha,
                        onValueChange = { novoValor -> senha = novoValor },
                        label = { Text("Senha") }
                    )
                    Text(
                        text = "Esqueci minha senha",
                        modifier = Modifier
                            .clickable { /* Ação para senha esquecida */ }
                            .padding(top = 12.dp, end = 39.dp)
                            .fillMaxWidth(),
                        color = ORANGE_SECUNDARY,
                        textAlign = TextAlign.End,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.weight(1f))
                BotaoSpan(
                    onClick = {

            loginViewModel.atualizarSenha(senha)

                        loginViewModel.atualizarEmail(email)
                        loginViewModel.atualizarSenha(senha)

                        loginViewModel.logar(context) { success ->
                            if (success) {
                                Log.d("Login", "Login realizado com sucesso!")
                                Toast.makeText(context, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()

                                // Navegação condicional com verificação de dtype
                                userDtype?.let {
                                    if (it.dtype == "Cliente") {
                                        navController.navigate("homeUsuario") {
                                            popUpTo("homeUsuario") { inclusive = true }
                                        }
                                    } else {
                                        navController.navigate("home") {
                                            popUpTo("home") { inclusive = true }
                                        }
                                    }
                                } ?: run {
                                    Log.d("Login", "DType não carregado, navegação padrão.")
                                    navController.navigate("homeUsuario") {
                                        popUpTo("homeUsuario") { inclusive = true }
                                    }
                                }
                            } else {
                                Log.d("Login", "Erro ao realizar o login.")
                                Toast.makeText(context, "Erro ao realizar o login. Tente novamente.", Toast.LENGTH_SHORT).show()
                            }
                        }
                              },
                    textButton = "Entrar",
                    textEsquerda = "Não possui conta?",
                    textDireita = "Cadastre-se",
                    onTextoDireitaClick = {
                        navController.navigate("home")
                    }
                )
            }
        }
    )
}


@Preview
@Composable
fun LoginPreview() {
    val navController = rememberNavController()
    Login(navController)
}

