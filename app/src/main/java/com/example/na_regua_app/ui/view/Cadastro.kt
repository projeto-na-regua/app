package com.example.na_regua_app.ui.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.data.api.UsuarioService
import com.example.na_regua_app.ui.components.Botao
import com.example.na_regua_app.ui.components.Input
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.viewmodel.CadastroViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Cadastro(
    navController: NavController,
    cadastroViewModel: CadastroViewModel = koinViewModel()
) {
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }
    val context = LocalContext.current


    var cep by remember { mutableStateOf("") }
    var logradouro by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
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
                Column(
                    modifier = Modifier
                        .padding(top = 18.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if(!mostrarPrimeirasInputs) {

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

                        Input(
                            modifier = Modifier.fillMaxWidth(),
                            value = nome,
                            onValueChange = { novoValor -> nome = novoValor },
                            label = { Text("Nome") }
                        )
                        Input(
                            modifier = Modifier.fillMaxWidth(),
                            value = email,
                            onValueChange = { novoValor -> email = novoValor },
                            label = { Text("E-mail") }
                        )
                        Input(
                            modifier = Modifier.fillMaxWidth(),
                            value = celular,
                            onValueChange = { novoValor -> celular = novoValor },
                            label = { Text("Celular") }
                        )
                        Input(
                            modifier = Modifier.fillMaxWidth(),
                            value = senha,
                            onValueChange = { novoValor -> senha = novoValor },
                            label = { Text("Senha") }
                        )
                        Input(
                            modifier = Modifier.fillMaxWidth(),
                            value = confirmarSenha,
                            onValueChange = { novoValor -> confirmarSenha = novoValor },
                            label = { Text("Confirmar senha") },
                            isError = senha != confirmarSenha // Marca como erro se as senhas forem diferentes
                        )
                    } else {

                        Text(
                            text = "Seu endereço",
                            fontSize = 24.sp,
                            color = BLUE_PRIMARY,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 40.dp),
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "Informe seu endereço para indicarmos a melhor barbearia para você.",
                            fontSize = 12.sp,
                            color = BLUE_PRIMARY,
                            modifier = Modifier.padding(top = 12.dp),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Medium,
                            letterSpacing = 1.sp,
                        )

                        Input(
                            modifier = Modifier.fillMaxWidth(),
                            value = cep,
                            onValueChange = { novoValor -> cep = novoValor },
                            label = { Text("Cep") }
                        )
                        Row (
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Input(
                                modifier = Modifier.weight(.7f),
                                value = logradouro,
                                onValueChange = { novoValor -> logradouro = novoValor },
                                label = { Text("Logradouro") }
                            )
                            Spacer(modifier = Modifier.weight(.05f))
                            Input(
                                modifier = Modifier.weight(.3f),
                                value = numero,
                                onValueChange = { novoValor -> numero = novoValor },
                                label = { Text("N") }
                            )
                        }
                        Input(
                            modifier = Modifier.fillMaxWidth(),
                            value = cidade,
                            onValueChange = { novoValor -> cidade = novoValor },
                            label = { Text("Cidade") }
                        )
                        Input(
                            modifier = Modifier.fillMaxWidth(),
                            value = estado,
                            onValueChange = { novoValor -> estado = novoValor },
                            label = { Text("Estado") }
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Botao(
                    onClick = {
                        if (botaoClicado && (senha == confirmarSenha)) {
                            // Atualiza as informações no ViewModel
                            cadastroViewModel.atualizarNome(nome)
                            cadastroViewModel.atualizarEmail(email)
                            cadastroViewModel.atualizarCelular(celular)
                            cadastroViewModel.atualizarSenha(senha)
                            cadastroViewModel.atualizarCep(cep)
                            cadastroViewModel.atualizarLogradouro(logradouro)
                            cadastroViewModel.atualizarNumero(numero)
                            cadastroViewModel.atualizarCidade(cidade)
                            cadastroViewModel.atualizarEstado(estado)

                            // Chama o método para enviar o cadastro
                            cadastroViewModel.enviarCadastro { success ->
                                if (success) {
                                    // Navega para a tela final de cadastro
                                    navController.navigate("cadastroFim")
                                } else {
                                    Toast.makeText(context, "Erro ao cadastrar. Tente novamente.", Toast.LENGTH_SHORT).show()
                                }
                            }
                        } else {
                            mostrarPrimeirasInputs = true
                            botaoClicado = true
                        }
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











