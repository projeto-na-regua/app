package com.example.na_regua_app.ui.view

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.na_regua_app.R
import com.example.na_regua_app.data.model.Funcionario
import com.example.na_regua_app.data.model.ServicoCardDTO
import com.example.na_regua_app.data.model.Usuario
import com.example.na_regua_app.data.model.usuarios
import com.example.na_regua_app.ui.components.BottomBarCustom
import com.example.na_regua_app.ui.components.ModalCadastroFuncionarios
import com.example.na_regua_app.ui.components.ModalCadastroServicos
import com.example.na_regua_app.ui.components.TopBarCustom
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.RED_DELETE
import com.example.na_regua_app.ui.view.dashboard.TituloIcon
import com.example.na_regua_app.viewmodel.FuncionarioViewModel
import com.example.na_regua_app.viewmodel.ServicoViewModel
import org.koin.compose.viewmodel.koinViewModel


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Gestao(
    navController: NavController,
    usuario: Usuario,
    funcionarioViewModel: FuncionarioViewModel = koinViewModel(),
    servicoViewModel: ServicoViewModel = koinViewModel()
) {
    Scaffold(
        topBar = {
            TopBarCustom(navController, "Gestão", true, false)
        },
        content = { paddingValues ->
            GestaoContent(paddingValues, funcionarioViewModel, servicoViewModel)
        },
        bottomBar = {
            BottomBarCustom(navController, LocalContext.current)
        }
    )
}
@Composable
fun GestaoContent(paddingValues: PaddingValues, funcionarioViewModel: FuncionarioViewModel, servicoViewModel: ServicoViewModel) {


    LaunchedEffect(Unit) {
        funcionarioViewModel.obterFuncionarios(0, true)
        servicoViewModel.obterServicosPorStatus("active", 0, true)
    }

    val funcionarios by funcionarioViewModel.funcionarios.collectAsState()
    val servicos by servicoViewModel.servicos.collectAsState()

    var showModalCadastroFuncionario by remember { mutableStateOf(false) }
    var showModalCadastroServico by remember { mutableStateOf(false) }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues) // Aqui aplicamos o padding gerado pelo Scaffold
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp) // Padding adicional que você quiser
        ) {
            item {
                ServicoSection(servicos, onShowModal = { showModalCadastroServico = true }) // Passa o callback
            }

            item {
                funcionarios?.let { FuncionarioSection(it, onShowModal = { showModalCadastroFuncionario = true }) } // Passa o callback
            }
        }

        if (showModalCadastroFuncionario) {
            ModalCadastroFuncionarios(onDismiss = { showModalCadastroFuncionario = false })
        }

        if(showModalCadastroServico){
            ModalCadastroServicos(onDismiss = { showModalCadastroServico = false})
        }
    }
}

@Composable
fun ServicoSection(servicos: List<ServicoCardDTO>, onShowModal: () -> Unit) { // Recebe callback
    TituloIcon("0s serviços cadastrados", R.drawable.novo_servico, onIconClick = { onShowModal() })

    Espacamento(18.dp)

    servicos.forEach {
        CardServico(
            title = it.tituloServico,
            descricao = it.descricao
        )
        Espacamento(18.dp)
    }

}

@Composable
fun FuncionarioSection(funcionarios: List<Funcionario>, onShowModal: () -> Unit) { // Recebe callback
    TituloIcon("0s funcionários cadastrados", R.drawable.add_user, onIconClick = { onShowModal() })

    Espacamento(18.dp)

    funcionarios.forEach {
        CardFuncionario(it)
        Espacamento(18.dp)
    }

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
        modifier = Modifier
            .border(2.dp, color = Color.Gray, shape = RoundedCornerShape(15.dp))
            .fillMaxWidth()
    ){

        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ){
            Box {
                Image(
                    painter = rememberAsyncImagePainter(model = funcionario.imgPerfil),
                    contentDescription = "Imagem do Funcionário",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                )
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