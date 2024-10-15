package com.example.na_regua_app.ui.view.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.na_regua_app.R
import com.example.na_regua_app.data.model.DiaSemana
import com.example.na_regua_app.data.model.Funcionario
import com.example.na_regua_app.data.model.ItemMenuDropDown
import com.example.na_regua_app.data.model.criarListaDiaSemana
import com.example.na_regua_app.ui.components.DropDownMenu
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.ui.theme.RED_DELETE
import com.example.na_regua_app.ui.theme.Typography
import com.example.na_regua_app.ui.view.Espacamento

@Composable
fun GerenciamentoContent() {
    // Usamos `remember` para manter o estado da lista durante a recomposição
    var semana by remember { mutableStateOf(criarListaDiaSemana()) }
    var funcionarios = listOf<Funcionario>(
        Funcionario(
            id = 0,
            nome= "Marcos V.",
            email =  "marcos@email.com",
            imgPerfil =  R.drawable.foto_perfil,
            dtype = "Barbeiro"
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        item {
            Text(
                text = "Horário de funcionamento",
                color = BLUE_PRIMARY,
                fontSize = 16.sp
            )

            Espacamento(15.dp)
        }


        items(semana) { dia ->
            CardDiaSemana(
                dia = dia,
                onStatusChanged = { updatedDia ->
                    semana = semana.map {
                        if (it.nome == updatedDia.nome) updatedDia else it
                    }
                }
            )
        }


        item {
            Espacamento(20.dp)
        }

        item {
            ServicoSection()
        }

        item {
            FuncionarioSection(funcionarios)
        }
    }
}

@Composable
fun ServicoSection(){

    TituloIcon("0s serviços cadastrados", R.drawable.novo_servico)

    Espacamento(18.dp)
    CardServico(
        title = "Corte + Escova",
        descricao = "Corte de cabelo e finalização com escova"
    )

    Espacamento(18.dp)
}

@Composable
fun FuncionarioSection(funcionarios: List<Funcionario>){

    TituloIcon("0s funcionários cadastrados", R.drawable.add_user)

    Espacamento(18.dp)

    funcionarios.forEach{
        CardFuncionario(it)
    }

    Espacamento(18.dp)
}



@Composable
fun CardDiaSemana(
    dia: DiaSemana,
    onStatusChanged: (DiaSemana) -> Unit
) {

    var isChecked by remember { mutableStateOf(dia.status) }

    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp)
    ) {
        Column {
            Text(
                dia.nome,
                fontSize = 14.sp
            )

            Espacamento(10.dp)

            if (isChecked) {
                Column (
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.width(300.dp)
                ) {


                    LinhaHorario("Abertura:", horario = dia.horaAbertura ?: "00:00:00")
                    LinhaHorario("Fechamento:  ", horario = dia.horaFechamento  ?: "00:00:00")


                }
            }
        }

        Switch(
            checked = isChecked,
            onCheckedChange = { checked ->
                isChecked = checked
                // Passar o novo estado do objeto modificado ao callback
                onStatusChanged(dia.copy(status = checked))
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = BLUE_PRIMARY, // Cor da bolinha quando está ativado
                uncheckedThumbColor = Color.Gray, // Cor da bolinha quando está desativado
                checkedTrackColor = Color.White, // Cor da faixa quando está ativado
                uncheckedTrackColor = Color.White, // Cor da faixa quando está desativado
                checkedBorderColor = Color.Gray,
                uncheckedBorderColor = Color.Gray,
                checkedIconColor = Color.White,

            )
        )



    }
}

@Composable
fun LinhaHorario(title: String, horario: String){
    Column (
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 10.dp)
    ){
        Text(title, fontSize = 12.sp)

        Row (
            verticalAlignment = Alignment.CenterVertically,
        ){
            DropDownMenu(
                items = getHoursList(),
                modifier = Modifier
                    .width(80.dp)
                    .height(45.dp)
                    .border(2.dp, color = Color.Gray, shape = RoundedCornerShape(15.dp)),
                menuWidth = 100.dp,
                tamFont = 10.sp,
                tamIcon = 10.dp,
                selectedItemPosition = filterList(getHoursList(), getHours(horario ?: "00"))
            )
            Text("h ", fontSize = 10.sp, modifier = Modifier.padding(start = 3.dp))
            Espacamento(10.dp)
            Text(" - ")

            Espacamento(10.dp)
            DropDownMenu(
                items = getMinutesList(),
                modifier = Modifier
                    .width(80.dp)
                    .height(45.dp)
                    .border(2.dp, color = Color.Gray, shape = RoundedCornerShape(15.dp)),
                menuWidth = 100.dp,
                tamFont = 10.sp,
                tamIcon = 10.dp,
                selectedItemPosition = filterList(getMinutesList(), getMinutes(horario ?: "00"))
            )
            Text("min", fontSize = 10.sp, modifier = Modifier.padding(start = 3.dp))
        }
    }

}


@Composable
fun CardServico(title: String, descricao: String){

    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.border(2.dp, color = Color.Gray, shape = RoundedCornerShape(15.dp)).fillMaxWidth()){
        Column (
            modifier = Modifier.padding(15.dp)
        ){
            Text(text = title,
                fontSize = 14.sp)
            Text(text = descricao,
                fontSize = 11.sp,
                fontWeight = FontWeight.Light
                )
        }

        Row {
            Icon(
                painter = painterResource(id = R.drawable.edit),
                contentDescription = "Update",
                tint = BLUE_PRIMARY,
                modifier = Modifier
                    .size(45.dp)
                    .padding(10.dp)
            )

            Icon(
                imageVector =  Icons.Default.Delete,
                contentDescription = "Delete",
                tint = RED_DELETE,
                modifier = Modifier
                    .size(45.dp)
                    .padding(10.dp)
            )
        }
    }
}

@Composable
fun CardFuncionario(funcionario: Funcionario){

    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.border(2.dp, color = Color.Gray, shape = RoundedCornerShape(15.dp)).fillMaxWidth()){

        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ){
            Box {
                if (funcionario.imgPerfil != null) {
                    Image(
                        painter = painterResource(id = funcionario.imgPerfil),
                        contentDescription = "Imagem do Funcionário",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Text(
                        text = "Foto Barbeiro",
                        style = Typography.labelMedium,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            Column (
                modifier = Modifier.padding(start = 15.dp)
            ){
                Text(text = funcionario.nome,
                    fontSize = 14.sp)
                Text(text = funcionario.email,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }



        Row {
            Icon(
                imageVector =  Icons.Default.Delete,
                contentDescription = "Delete",
                tint = RED_DELETE,
                modifier = Modifier
                    .size(45.dp)
                    .padding(10.dp)
            )
        }
    }
}


@Composable
fun TituloIcon(title: String, icon: Int){

    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ){
        Text(
            text = title,
            color = BLUE_PRIMARY,
            fontSize = 16.sp
        )

        Box(
            modifier = Modifier.background(BLUE_PRIMARY, shape = RoundedCornerShape(10.dp))
        ){
            Icon(
                painter = painterResource(id = icon),
                contentDescription = title,
                tint = ORANGE_SECUNDARY,
                modifier = Modifier
                    .size(50.dp)
                    .padding(10.dp)
            )
        }
    }
}

fun getHoursList(): List<ItemMenuDropDown> {
    return (0..23).map { hour ->
        ItemMenuDropDown(name = hour.toString().padStart(2, '0'))
    }
}

fun getMinutesList(): List<ItemMenuDropDown> {
    return listOf(
        ItemMenuDropDown(name = "00"),
        ItemMenuDropDown(name = "05"),
        ItemMenuDropDown(name = "10"),
        ItemMenuDropDown(name = "15"),
        ItemMenuDropDown(name = "20"),
        ItemMenuDropDown(name = "25"),
        ItemMenuDropDown(name = "30"),
        ItemMenuDropDown(name = "35"),
        ItemMenuDropDown(name = "40"),
        ItemMenuDropDown(name = "45"),
        ItemMenuDropDown(name = "50"),
        ItemMenuDropDown(name = "55")
    )
}

fun filterList(listItem: List<ItemMenuDropDown>, hora: String): ItemMenuDropDown? {
    return listItem
        .filter { it.name == hora }
        .sortedBy { it.name }
        .firstOrNull()
}

fun getHours(time: String): String {
    return time.substring(0, 2)
}

fun getMinutes(time: String): String {
    return time.substring(3, 5)
}

