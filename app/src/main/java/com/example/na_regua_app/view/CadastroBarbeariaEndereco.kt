package com.example.na_regua_app.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.na_regua_app.components.Botao
import com.example.na_regua_app.components.BotaoSpan
import com.example.na_regua_app.components.Input
import com.example.na_regua_app.components.LogoImage
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY

@Composable
fun CadastroBarbeariaEndereco(
    navController: NavController
) {
    var cep by remember { mutableStateOf("") }
    var logradouro by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }
    var bairro by remember { mutableStateOf("") }
    var numero by remember {
        mutableStateOf("")
    }

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
                Text(
                    text = "Endereço da sua barbearia",
                    fontSize = 24.sp,
                    color = BLUE_PRIMARY,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 40.dp),
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Informe o endereço de sua barbearia para seus clientes te encontrarem.",
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
                        value = bairro,
                        onValueChange = { novoValor -> bairro = novoValor },
                        label = { Text("Bairro") }
                    )
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

                Spacer(modifier = Modifier.weight(1f))
                Botao(
                    onClick = {
                    },
                    textButton =  "Próximo"
                )

            }
        }
    )
}

@Preview
@Composable
fun CadastroBarbeariaEnderecoPreview() {
    CadastroBarbeariaEndereco(navController = rememberNavController())
}