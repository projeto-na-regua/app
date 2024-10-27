package com.example.na_regua_app.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import com.example.na_regua_app.data.model.NovoServico
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.viewmodel.FuncionarioViewModel
import com.example.na_regua_app.viewmodel.ServicoViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ModalCadastroServicos(onDismiss: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        val context = LocalContext.current

        // Camada de fundo que permite fechar o modal ao clicar fora
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000)) // Cor de fundo com transparência
                .clickable(onClick = onDismiss) // Fecha ao clicar fora
        )

        // Conteúdo do modal
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(20.dp)
                .background(Color.White, RoundedCornerShape(12.dp))
                .heightIn(max = 500.dp) // Define altura máxima
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp), // Espaçamento interno do modal
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Cabeçalho do modal
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
                        text = "Cadastro serviço",
                        color = BLUE_PRIMARY,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        modifier = Modifier.padding(start = 60.dp),
                        letterSpacing = .5.sp
                    )
                }

                // Conteúdo do modal
                ContentModalCadastroServicos(onDismiss, context = context)
            }
        }
    }
}

@Composable
fun ContentModalCadastroServicos(
    onDismiss: () -> Unit,
    funcionarioViewModel: FuncionarioViewModel = koinViewModel(),
    servicoViewModel: ServicoViewModel = koinViewModel(),
    context: Context
) {
    var preco by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var tipoServico by remember { mutableStateOf("") }
    var tempoEstimado by remember { mutableStateOf("") }
    var showTempoOptions by remember { mutableStateOf(false) }
    var barbeirosEmails = remember { mutableStateListOf<String>() }
    val funcionarios by funcionarioViewModel.funcionarios.collectAsState()

    LaunchedEffect(Unit) {
        funcionarioViewModel.obterFuncionarios(0, true)
    }

    // Lista de opções para tempo estimado
    val tempoOptions = listOf("15min", "30min", "45min", "1h") + (90..180 step 30).map { "${it / 60}h ${it % 60}min" }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        InputCadastro(value = tipoServico, onValueChange = { tipoServico = it }, label = { Text("Nome do Serviço") })
        Spacer(modifier = Modifier.height(8.dp))

        InputCadastro(value = descricao, onValueChange = { descricao = it }, label = { Text("Descrição") })
        Spacer(modifier = Modifier.height(8.dp))

        InputCadastro(value = preco, onValueChange = { preco = it }, label = { Text("Preço") })
        Spacer(modifier = Modifier.height(8.dp))

        // Campo de Tempo Estimado com Dropdown
        Text(text = "Tempo estimado", fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 5.dp), color = BLUE_PRIMARY)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .clickable { showTempoOptions = !showTempoOptions }
                .border(shape = RoundedCornerShape(8.dp), width = 1.dp, color = BLUE_PRIMARY)
                .padding(12.dp)
        ) {
            Text(
                text = if (tempoEstimado.isNotEmpty()) tempoEstimado else "Selecione o tempo",
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                color = BLUE_PRIMARY
            )
        }
        if (showTempoOptions) {
            Column(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(4.dp))
                    .padding(4.dp)
                    .border(shape = RoundedCornerShape(8.dp), width = 1.dp, color = BLUE_PRIMARY)
                    .fillMaxWidth()
                    .heightIn(max = 200.dp) // Limita a altura do dropdown
                    .verticalScroll(rememberScrollState())
            ) {
                tempoOptions.forEach { option ->
                    Text(
                        text = option,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                tempoEstimado = option
                                showTempoOptions = false
                            }
                            .padding(8.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Lista de Seleção de Funcionários com Checkboxes
        Text("Selecionar funcionários:")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 200.dp) // Limita a altura da lista de funcionários
                .verticalScroll(rememberScrollState())
        ) {
            funcionarios?.forEach { funcionario ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (barbeirosEmails.contains(funcionario.email)) {
                                barbeirosEmails.remove(funcionario.email)
                            } else {
                                barbeirosEmails.add(funcionario.email)
                            }
                        }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = barbeirosEmails.contains(funcionario.email),
                        onCheckedChange = { isChecked ->
                            if (isChecked) {
                                barbeirosEmails.add(funcionario.email)
                            } else {
                                barbeirosEmails.remove(funcionario.email)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = funcionario.nome)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botões de Ação
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BotaoAjustavel(
                onClick = onDismiss,
                shape = RoundedCornerShape(10.dp),
                textButton = "Cancelar",
                fontSize = 14,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(50.dp))
            BotaoAjustavel(
                onClick = {
                    // Converte o tempo estimado para Int (em minutos)
                    val tempoEstimadoInt = converterTempoParaMinutos(tempoEstimado)

                    val novoServico = NovoServico(
                        preco.toDouble(),
                        descricao,
                        tipoServico,
                        tempoEstimadoInt,
                        barbeirosEmails
                    )

                    servicoViewModel.criarServico(novoServico) { success ->
                        if (success) {
                            Toast.makeText(context, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                            servicoViewModel.obterServicosPorStatus("active", 0, true)
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



fun converterTempoParaMinutos(tempo: String): Int {
    return when {
        tempo.endsWith("min") -> tempo.replace("min", "").toInt()
        tempo.endsWith("h") -> tempo.replace("h", "").toInt() * 60
        else -> 0 // Caso não seja um tempo válido, retorna 0
    }
}


@Preview
@Composable
fun ModalCadastroServicosPreview() {
    ModalCadastroServicos(onDismiss = { /* Fechar o modal aqui */ })
}
