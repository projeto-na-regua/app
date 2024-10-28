package com.example.na_regua_app.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.na_regua_app.R
import com.example.na_regua_app.data.model.AgendamentoConsulta
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ModalHistorico(agendamento: AgendamentoConsulta, onDismiss: () -> Unit) {
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
                        text = agendamento.nomeNegocio,
                        color = BLUE_PRIMARY,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        modifier = Modifier.padding(start = 60.dp),
                        letterSpacing = .5.sp
                    )
                }
            }
            item {
                ContentModalHistorico(agendamento)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContentModalHistorico(agendamento: AgendamentoConsulta) {
    val dataFormatada = com.example.na_regua_app.utils.formatarDataHora(agendamento.dataHora.toString(), apenasData = true)

    Box(
        modifier = Modifier
            .border(1.dp, color = BLUE_PRIMARY, RoundedCornerShape(12.dp))
            .padding(vertical = 15.dp)
            .padding(horizontal = 20.dp)
            .fillMaxWidth(.9f)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = agendamento.tipoServico,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = BLUE_PRIMARY
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = dataFormatada,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    color = BLUE_PRIMARY
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            if (agendamento.comentario == null) {
                Button(
                    onClick = { /*TODO: Implementar a ação de enviar avaliação*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    shape = RoundedCornerShape(7.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BLUE_PRIMARY,
                        contentColor = ORANGE_SECUNDARY
                    )
                ) {
                    Text(text = "Enviar avaliação", fontSize = 12.sp, color = ORANGE_SECUNDARY)
                }
            } else {
                Button(
                    onClick = { /* Ação desativada */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    shape = RoundedCornerShape(7.dp),
                    enabled = false, // Desativa o botão no estado "Avaliação enviada"
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFBDBDBD), // Cinza claro para o fundo
                        contentColor = Color(0xFF616161) // Cinza escuro para o texto
                    )
                ) {
                    Text(text = "Avaliação enviada", fontSize = 12.sp, color = Color(0xFF616161))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ModalHistoricoPreview() {
    ModalHistorico(onDismiss = { /* Handle dismiss */ })
}

fun ModalHistorico(onDismiss: () -> Unit) {
    TODO("Not yet implemented")
}
