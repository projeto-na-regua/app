package com.example.na_regua_app.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.na_regua_app.R
import com.example.na_regua_app.data.model.NovoBarbeiro
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.viewmodel.FuncionarioViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ModalCadastroFuncionarios(onDismiss: () -> Unit) {

    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Camada de fundo que permite fechar o modal ao clicar fora
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000)) // Cor de fundo com transparência
                .clickable(onClick = onDismiss) // Fecha ao clicar fora
        )

        // Conteúdo do modal
        LazyColumn(
            modifier = Modifier
                .height(500.dp)
                .align(Alignment.Center)
                .padding(20.dp)
                .background(Color.White, RoundedCornerShape(12.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icone_fechar),
                        contentDescription = "Ícone fechar",
                        modifier = Modifier
                            .clickable(onClick = onDismiss) // Fecha ao clicar no ícone
                            .size(24.dp) // Define um tamanho para o ícone, se necessário
                    )
                    Text(
                        text = "Cadastro funcionário",
                        color = BLUE_PRIMARY,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        modifier = Modifier.padding(start = 60.dp),
                        letterSpacing = .5.sp
                    )
                }
            }
            item {
                ContentModalCadastroFuncionarios(onDismiss, context = context)
            }
        }
    }
}


@Composable
fun ContentModalCadastroFuncionarios(
    onDismiss: () -> Unit,
    funcionarioViewModel: FuncionarioViewModel = koinViewModel(),
    context: Context
) {
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        InputCadastro(value = nome, onValueChange = { nome = it }, label = { Text("Nome") })
        Spacer(modifier = Modifier.height(8.dp))

        InputCadastro(value = email, onValueChange = { email = it }, label = { Text("E-mail") })
        Spacer(modifier = Modifier.height(8.dp))

        InputCadastro(value = senha, onValueChange = { senha = it }, label = { Text("Senha") })
        Spacer(modifier = Modifier.height(8.dp))

        InputCadastro(value = celular, onValueChange = { celular = it }, label = { Text("Celular") })
        Spacer(modifier = Modifier.height(16.dp))

        var novoBarbeiro = NovoBarbeiro(
            nome,
            email,
            senha,
            celular
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BotaoAjustavel(
                onClick = {
                    onDismiss()
                },
                shape = RoundedCornerShape(10.dp),
                textButton = "Cancelar",
                fontSize = 14,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(50.dp))
            BotaoAjustavel(
                onClick = {
                    funcionarioViewModel.cadastrarFuncionario(novoBarbeiro) { success ->
                        if (success) {
                            Toast.makeText(context, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                            funcionarioViewModel.obterFuncionarios(0, true) // Atualiza a lista de funcionários
                            onDismiss()
                        } else {
                            Toast.makeText(context, "Algo deu errado, tente novamente!", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                shape = RoundedCornerShape(10.dp),
                textButton = "Enviar",
                fontSize = 14,
                modifier = Modifier.weight(1f)
            )
        }

    }
}


@Preview
@Composable
fun ModalCadastroFuncionariosPreview() {
    ModalCadastroFuncionarios(onDismiss = { /* Fechar o modal aqui */ })
}