package com.example.na_regua_app.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.R
import com.example.na_regua_app.classes.Funcionario
import com.example.na_regua_app.classes.Usuario
import com.example.na_regua_app.classes.usuarios
import com.example.na_regua_app.components.BottomBarCustom
import com.example.na_regua_app.components.TopBarCustom
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.RED_DELETE
import com.example.na_regua_app.ui.theme.Typography
import com.example.na_regua_app.view.dashboard.TituloIcon


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Gestao(
    navController: NavController,
    usuario: Usuario
) {
    Scaffold(
        topBar = {
            TopBarCustom(navController, "Gestão", true)
        },
        content = { paddingValues ->
            GestaoContent(paddingValues)
        },
        bottomBar = {
            BottomBarCustom(navController, usuario)
        }
    )
}
@Composable
fun GestaoContent(paddingValues: PaddingValues) {
    // Usamos `remember` para manter o estado da lista durante a recomposição
    var funcionarios = listOf<Funcionario>(
        Funcionario(
            id = 0,
            nome = "Marcos V.",
            email = "marcos@email.com",
            imgPerfil = R.drawable.foto_perfil,
            dtype = "Barbeiro"
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues) // Aqui aplicamos o padding gerado pelo Scaffold
            .padding(20.dp)         // Padding adicional que você quiser
    ) {

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
fun CardServico(title: String, descricao: String){

    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .border(2.dp, color = Color.Gray, shape = RoundedCornerShape(15.dp))
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .weight(1f) // Distribui a largura de forma igual
        ) {
            Text(
                text = title,
                fontSize = 14.sp
            )
            Text(
                text = descricao,
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
                imageVector = Icons.Default.Delete,
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun GestaoPreview(){

    val navController = rememberNavController()
    Gestao(navController = navController, usuarios().first())
}