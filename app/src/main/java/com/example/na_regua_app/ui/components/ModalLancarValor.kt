package com.example.na_regua_app.ui.components

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.na_regua_app.R
import com.example.na_regua_app.data.model.FinancaCriacao
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.viewmodel.FinancaViewModel
import org.koin.compose.viewmodel.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ModalLancarValor(isModalOpen: Boolean, onDismiss: () -> Unit) {
    if (isModalOpen) {
        Dialog(onDismissRequest = onDismiss) {
            Box(
                modifier = Modifier
                    .padding(20.dp)
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .fillMaxWidth()
                    .heightIn(max = 400.dp) // Limita a altura do modal
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icone_fechar),
                            contentDescription = "Ícone fechar",
                            modifier = Modifier
                                .clickable(onClick = onDismiss)
                                .size(24.dp)
                        )
                        Text(
                            text = "Lançar valor",
                            color = BLUE_PRIMARY,
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600),
                            modifier = Modifier.padding(start = 50.dp),
                            letterSpacing = .5.sp
                        )
                    }
                    ContentModalLancarValor(onDismiss, context = LocalContext.current)
                }
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContentModalLancarValor(
    onDismiss: () -> Unit,
    financaViewModel: FinancaViewModel = koinViewModel(),
    context: Context
) {
    var preco by remember { mutableStateOf("") }
    var despesa by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        InputPreco(value = preco, onValueChange = { preco = it }, label = { Text("Preço") })

        Spacer(modifier = Modifier.height(16.dp))

        CampoDespesaReceita(
            despesa = despesa,
            onDespesaReceitaSelecionado = { isDespesa ->
                despesa = isDespesa
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        BotaoAjustavel(
            onClick = {
                val despesaText = if (despesa) "Despesa" else "Receita"
                val financaCriacao = FinancaCriacao(
                    preco.toDoubleOrNull() ?: 0.0,
                    despesa
                )

                financaViewModel.lancarValor(financaCriacao) { success ->
                    if (success) {
                        Toast.makeText(context, "$despesaText lançada com sucesso!", Toast.LENGTH_SHORT).show()
                        onDismiss()
                    } else {
                        Toast.makeText(context, "Algo deu errado, tente novamente!", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            shape = RoundedCornerShape(10.dp),
            textButton = "Enviar",
            fontSize = 14,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
    }
}

@Composable
fun CampoDespesaReceita(
    despesa: Boolean,
    onDespesaReceitaSelecionado: (Boolean) -> Unit
) {
    var showOpcao by remember { mutableStateOf(false) }
    val opcoes = listOf("Despesa", "Receita")

    // Texto do título
    Text(
        text = "Tipo de Lançamento",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 5.dp),
        color = BLUE_PRIMARY
    )

    // Caixa para selecionar Despesa ou Receita
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .clickable { showOpcao = !showOpcao }
            .border(shape = RoundedCornerShape(8.dp), width = 1.dp, color = BLUE_PRIMARY)
            .padding(12.dp)
    ) {
        Text(
            text = if (despesa) "Despesa" else "Receita",
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            color = BLUE_PRIMARY
        )
    }

    // Dropdown com opções de Despesa e Receita
    if (showOpcao) {
        Column(
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(4.dp))
                .padding(4.dp)
                .border(shape = RoundedCornerShape(8.dp), width = 1.dp, color = BLUE_PRIMARY)
                .fillMaxWidth()
                .heightIn(max = 200.dp) // Limita a altura do dropdown
                .verticalScroll(rememberScrollState())
        ) {
            opcoes.forEach { option ->
                Text(
                    text = option,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onDespesaReceitaSelecionado(option == "Despesa") // Atualiza o estado
                            showOpcao = false
                        }
                        .padding(8.dp)
                )
            }
        }
    }
}


