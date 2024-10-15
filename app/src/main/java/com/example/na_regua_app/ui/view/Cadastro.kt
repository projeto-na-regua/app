package com.example.na_regua_app.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.ui.components.Botao
import com.example.na_regua_app.ui.components.BotaoSpan
import com.example.na_regua_app.ui.components.Input
import com.example.na_regua_app.ui.components.LogoImage
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY

@Composable
fun Cadastro(
    navController: NavController
) {
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }

    var cep by remember { mutableStateOf("") }
    var logradouro by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }

    var mostrarPrimeirasInputs by remember { mutableStateOf(false) }
    var botaoClicado by remember { mutableStateOf(false) }

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
                    text = "Informações pessoais!",
                    fontSize = 24.sp,
                    color = BLUE_PRIMARY,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 40.dp),
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Para iniciarmos seu cadastro informe seu dados!",
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
                    if(!mostrarPrimeirasInputs) {
                        Input(
                            value = nome,
                            onValueChange = { novoValor -> nome = novoValor },
                            label = { Text("Nome") }
                        )
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
                        Input(
                            value = confirmarSenha,
                            onValueChange = { novoValor -> confirmarSenha = novoValor },
                            label = { Text("Confirmar senha") }
                        )
                    } else {
                        Input(
                            value = cep,
                            onValueChange = { novoValor -> cep = novoValor },
                            label = { Text("Cep") }
                        )
                        Input(
                            value = logradouro,
                            onValueChange = { novoValor -> logradouro = novoValor },
                            label = { Text("Logradouro") }
                        )
                        Input(
                            value = cidade,
                            onValueChange = { novoValor -> cidade = novoValor },
                            label = { Text("Cidade") }
                        )
                        Input(
                            value = estado,
                            onValueChange = { novoValor -> estado = novoValor },
                            label = { Text("Estado") }
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Botao(
                    onClick = {
                        if(botaoClicado) {
                            navController.navigate("cadastroFim")
                        }
                        mostrarPrimeirasInputs = true
                        botaoClicado = true
                    },
                    textButton = "Próximo"
                )

            }
        }
    )
}

@Preview
@Composable
fun CadastroPreview() {
    Cadastro(navController = rememberNavController())
}