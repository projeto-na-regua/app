package com.example.na_regua_app.ui.view.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.na_regua_app.R
import com.example.na_regua_app.data.model.AgendamentoBarbeiro
import com.example.na_regua_app.data.model.ItemMenuDropDown
import com.example.na_regua_app.data.model.criarListaAgendamentos
import com.example.na_regua_app.ui.components.DropDownMenu
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.GREEN_CONFIRMAR
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.ui.view.Espacamento
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun AgendamentosContent() {
    var qtdAgendamentos by remember { mutableStateOf("3") }
    var agendamentos = criarListaAgendamentos()

    var agPendentes = agendamentos.filter { it.status == "Pendente" }
    var agConfirmados = agendamentos.filter { it.status == "Agendado" }
    var agConcluidos = agendamentos.filter { it.status == "Conluido" }
    var agCancelado = agendamentos.filter { it.status == "Cancelado" }

    Espacamento(20.dp)

    Column {
        SectionAgendamentos(

            actionNames = listOf(
                ItemMenuDropDown(
                name = "Confirmar Agendamento",
                colorBefore = ORANGE_SECUNDARY,
                colorAfter = GREEN_CONFIRMAR,
                nameAfter = "Confirmado"
            ),
                ItemMenuDropDown(
                    name = "Cancelar agendamento",
                    colorBefore = Color.Red,
                    colorAfter = Color.Red,
                    nameAfter = "Cancelado"
                )
            ),
            agendamentos = agPendentes,
            statusAgendamentos = pluralStringResource(
                id = R.plurals.pendentes,
                count = agPendentes.size, agPendentes.size
            )
        )

        Espacamento(20.dp)


        SectionAgendamentos(
            actionNames = listOf(
                ItemMenuDropDown(
                name = "Concluir Agendamento",
                nameAfter ="Concluído",
                colorBefore = GREEN_CONFIRMAR,
                colorAfter = GREEN_CONFIRMAR
            ),
                ItemMenuDropDown(
                    name = "Cancelar agendamento",
                    nameAfter = "Cancelado",
                    colorBefore = Color.Red,
                    colorAfter = Color.Red
                )
            ),
            agendamentos = agConfirmados,
            statusAgendamentos = pluralStringResource(
                id = R.plurals.confirmado,
                count = agConfirmados.size, agConfirmados.size
            )
        )
    }
}


@Composable
fun SectionAgendamentos(
    actionNames: List<ItemMenuDropDown>,
    agendamentos: List<AgendamentoBarbeiro>,
    statusAgendamentos: String){


    Column (
        modifier = Modifier.padding(20.dp, top = 0.dp)
    ) {
        Text(text = statusAgendamentos,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium)

        Espacamento(10.dp)

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(agendamentos) {
                CardAgendamento(it, actionNames)
            }

        }


    }

}


@Composable
fun CardAgendamento(agendamentoBarbeiro: AgendamentoBarbeiro, actionsName: List<ItemMenuDropDown>) {

    Column (
        modifier = Modifier.width(250.dp)
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(15.dp))
    ){
        Row (
            modifier = Modifier.fillMaxWidth().padding(15.dp, bottom = 0.dp, top = 15.dp)
        ){
            Image(
                painter = rememberAsyncImagePainter(agendamentoBarbeiro.imgCliente),
                contentDescription = "Imagem do cliente",
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .border(2.dp, BLUE_PRIMARY, shape = RoundedCornerShape(12.dp))
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Column (
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(8.dp, 0.dp).height(50.dp)
            ){
                Text(text = agendamentoBarbeiro.nomeCliente,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal)
                Text(text = formatarDataHoraAg(agendamentoBarbeiro.dataHora),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light)
            }
        }

        DropDownMenu(agendamentoBarbeiro, actionsName, modifier = Modifier.fillMaxWidth(), menuWidth = 250.dp,  tamFont = 15.sp)
    }
}


fun updateStatus(agendamentoBarbeiro: AgendamentoBarbeiro, statusAgendamentos: String){
    agendamentoBarbeiro.status = statusAgendamentos
}

fun actions(agendamento: AgendamentoBarbeiro, itemSelected: String){

    when (itemSelected) {
        "Confirmar Agendamento" -> updateStatus(agendamento, "Agendado")
        "Cancelar Agendamento" -> updateStatus(agendamento, "Cancelado")
        "Concluir Agendamento" -> updateStatus(agendamento, "Concluido")
        else -> {
            println("Opção desconhecida: $itemSelected")
        }
    }

}


@SuppressLint("NewApi")
fun formatarDataHoraAg(dataHora: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - H'h'")
    return dataHora.format(formatter)
}