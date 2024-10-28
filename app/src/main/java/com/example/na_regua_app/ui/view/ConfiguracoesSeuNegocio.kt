import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.na_regua_app.R
import com.example.na_regua_app.data.model.Barbearia
import com.example.na_regua_app.data.model.BarbeariaDTO
import com.example.na_regua_app.data.model.DiaSemana
import com.example.na_regua_app.ui.components.Botao
import com.example.na_regua_app.ui.components.BotaoComIcone
import com.example.na_regua_app.ui.components.BottomBarCustom
import com.example.na_regua_app.ui.components.TopBarCustom
import com.example.na_regua_app.data.model.Usuario
import com.example.na_regua_app.data.model.usuarios
import com.example.na_regua_app.ui.theme.textSection
import com.example.na_regua_app.ui.theme.titleSection
import com.example.na_regua_app.ui.theme.titleSectionBold
import com.example.na_regua_app.viewmodel.PerfilBarbeariaViewModel
import com.example.na_regua_app.viewmodel.PerfilUsuarioViewModel
import org.koin.compose.viewmodel.koinViewModel
import com.example.na_regua_app.ui.components.Input
import com.example.na_regua_app.ui.components.Botao
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ConfiguracoesSeuNegocio(
    navController: NavController,
    usuario: Usuario,
    barbeariaViewModel: PerfilBarbeariaViewModel = koinViewModel(),
) {
    var showEditDialog by remember { mutableStateOf(false) }
    val barbeariaBody by barbeariaViewModel.barbearia.collectAsState()

    Scaffold(
        topBar = {
            TopBarCustom(navController, "Configurações", false, false, true)
        },
        content = { paddingValues ->

            if (barbeariaBody != null) {

                val nomeNegocio by remember { mutableStateOf(barbeariaBody!!.nomeNegocio) }
                val emailNegocio by remember { mutableStateOf(barbeariaBody!!.emailNegocio) }
                val celularNegocio by remember { mutableStateOf(barbeariaBody!!.celularNegocio) }
                val descricao by remember { mutableStateOf(barbeariaBody!!.descricao) }
                val cep by remember { mutableStateOf(barbeariaBody!!.cep) }
                val logradouro by remember { mutableStateOf(barbeariaBody!!.logradouro) }
                val numero by remember { mutableStateOf(barbeariaBody!!.numero) }
                val complemento by remember { mutableStateOf(barbeariaBody!!.complemento) }
                val cidade by remember { mutableStateOf(barbeariaBody!!.cidade) }
                val estado by remember { mutableStateOf(barbeariaBody!!.estado) }
                val diaSemanas by remember { mutableStateOf(barbeariaBody!!.diaSemanas) }
                val imgPerfil by remember { mutableStateOf(barbeariaBody!!.imgPerfil) }
                val imgBanner by remember { mutableStateOf(barbeariaBody!!.imgBanner) }

                var imagemSelecionadaBanner by remember { mutableStateOf<Uri?>(null) }
                var imagemSelecionadaPerfil by remember { mutableStateOf<Uri?>(null) }

                val galleryLauncherBanner = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent()
                ) { uri: Uri? ->
                    imagemSelecionadaBanner = uri
                }

                val galleryLauncherPerfil = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent()
                ) { uri: Uri? ->
                    imagemSelecionadaPerfil = uri
                }

                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Image(
                                        painter = rememberAsyncImagePainter(model = imgBanner),
                                        contentDescription = "Imagem de fundo da Barbearia",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(150.dp)
                                            .border(
                                                1.dp,
                                                Color(0xFFCBD5E0),
                                                RoundedCornerShape(12.dp)
                                            ),
                                    )

                                    Box(
                                        modifier = Modifier
                                            .offset(y = (-70).dp, x = 24.dp)
                                    ) {
                                        println(imgPerfil)
                                        Image(
                                            painter = rememberAsyncImagePainter(model = imgPerfil),
                                            contentDescription = "Imagem de perfil da Barbearia",
                                            modifier = Modifier
                                                .size(100.dp)
                                                .clip(CircleShape)
                                                .border(1.dp, Color(0xFFCBD5E0), CircleShape)
                                        )
                                    }

                                    Button(
                                        onClick = {
                                           // galleryLauncher.launch("image/*")
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = BLUE_PRIMARY
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth(.55f)
                                            .padding(top = 15.dp),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = com.example.na_regua_app.R.drawable.icone_adicionar_foto),
                                            contentDescription = "",
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Spacer(modifier = Modifier.width(20.dp))
                                        Text(
                                            text = "Adicionar foto",
                                            color = ORANGE_SECUNDARY,
                                            fontSize = 14.sp,
                                            letterSpacing = 1.sp,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                            }


                            Column(
                                modifier = Modifier
                                    .offset(y = (-50).dp)
                            ) {
                                Text(text = "Seu Negócio", style = titleSectionBold)

                                Spacer(modifier = Modifier.height(24.dp))

                                Box {
                                    Column {
                                        Text(text = "Nome da Barbearia", style = titleSection)

                                        Spacer(modifier = Modifier.height(4.dp))

                                        Text(text = nomeNegocio, style = textSection)
                                    }
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                Box {
                                    Column {
                                        Text(text = "Telefone", style = titleSection)

                                        Spacer(modifier = Modifier.height(4.dp))

                                        Text(text = celularNegocio, style = textSection)
                                    }
                                }

                                Spacer(modifier = Modifier.height(32.dp))

                                Text(text = "Informações de Endereço", style = titleSectionBold)

                                Spacer(modifier = Modifier.height(24.dp))

                                Box {
                                    Column {
                                        Text(text = "Endereço", style = titleSection)

                                        Spacer(modifier = Modifier.height(4.dp))

                                        Text(
                                            text = "$logradouro, $numero - $complemento - $cep",
                                            style = textSection
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                Box {
                                    Column {
                                        Text(text = "Cidade/Estado", style = titleSection)

                                        Spacer(modifier = Modifier.height(4.dp))

                                        Text(
                                            text = "$cidade - $estado",
                                            style = textSection
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(32.dp))

                                Text(text = "Mais informações", style = titleSectionBold)

                                Spacer(modifier = Modifier.height(24.dp))

                                Box {
                                    Column {
                                        Text(text = "Descrição", style = titleSection)

                                        Spacer(modifier = Modifier.height(4.dp))

                                        Text(
                                            text = "$descricao",
                                            style = textSection
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(32.dp))

//                                Button(
//                                    onClick = {
//                                        navController.navigate("deletebusiness")
//                                    },
//                                    colors = ButtonDefaults.buttonColors(
//                                        containerColor = Color.Transparent,
//                                        contentColor = Color(0xFFCC2828)
//                                    ),
//                                    border = BorderStroke(1.dp, Color(0xFFCC2828)),
//                                    shape = RoundedCornerShape(12.dp),
//                                    contentPadding = PaddingValues(16.dp),
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                ) {
//                                    Row(
//                                        modifier = Modifier.fillMaxWidth(),
//                                        horizontalArrangement = Arrangement.SpaceBetween,
//                                        verticalAlignment = Alignment.CenterVertically
//                                    ) {
//                                        Text(
//                                            text = "Excluir barbearia",
//                                            style = androidx.compose.ui.text.TextStyle(
//                                                color = Color(0xFFCC2828),
//                                                fontSize = 16.sp,
//                                                fontWeight = FontWeight.Bold
//                                            )
//                                        )
//
//                                        Icon(
//                                            imageVector = Icons.Default.KeyboardArrowRight,
//                                            contentDescription = null
//                                        )
//                                    }
//                                }
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            BotaoComIcone(
                                onClick = { showEditDialog = true }, // Abre o diálogo ao clicar
                                textButton = "Editar Informações",
                                imagePainter = painterResource(id = R.drawable.icon_edit),
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(text = "Desenvolvido por NaRégua", style = textSection)
                        }
                    }
                }
            }
        },
        bottomBar = {
            BottomBarCustom(navController, LocalContext.current)
        }
    )

    // Mostrar o diálogo se showEditDialog for verdadeiro
    if (showEditDialog) {
        EditarInformacoesDialog(
            onDismiss = { showEditDialog = false }, // Fecha o diálogo ao chamar onDismiss
            barbeariaViewModel = barbeariaViewModel // Passa o ViewModel para o diálogo
        )
    }
}

@Composable
fun EditarInformacoesDialog(
    onDismiss: () -> Unit,
    barbeariaViewModel: PerfilBarbeariaViewModel = koinViewModel(), // ViewModel
) {

    val barbeariaBody by barbeariaViewModel.barbearia.collectAsState()

    var nomeNegocio by remember { mutableStateOf(barbeariaBody!!.nomeNegocio) }
    var emailNegocio by remember { mutableStateOf(barbeariaBody!!.emailNegocio) }
    var celularNegocio by remember { mutableStateOf(barbeariaBody!!.celularNegocio) }
    var descricao by remember { mutableStateOf(barbeariaBody!!.descricao) }
    var cep by remember { mutableStateOf(barbeariaBody!!.cep) }
    var logradouro by remember { mutableStateOf(barbeariaBody!!.logradouro) }
    var numero by remember { mutableStateOf(barbeariaBody!!.numero.toString()) }
    var complemento by remember { mutableStateOf(barbeariaBody!!.complemento) }
    var cidade by remember { mutableStateOf(barbeariaBody!!.cidade) }
    var estado by remember { mutableStateOf(barbeariaBody!!.estado) }
    var diaSemanas by remember { mutableStateOf(barbeariaBody!!.diaSemanas) }

    val context = LocalContext.current

    // Estado para mostrar mensagens de erro ou sucesso
    val errorMessage = remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Editar Informações da Barbearia") },
        text = {
            LazyColumn {
                items(9) { index ->
                    when (index) {
                        0 -> Input(
                            value = nomeNegocio,
                            onValueChange = { nomeNegocio = it },
                            label = { Text("Nome do Negócio") })

                        1 -> Input(
                            value = emailNegocio,
                            onValueChange = { emailNegocio = it },
                            label = { Text("E-mail do Negócio") })

                        2 -> Input(
                            value = celularNegocio,
                            onValueChange = { celularNegocio = it },
                            label = { Text("Telefone do Negócio") })

                        3 -> Input(
                            value = descricao ?: "",
                            onValueChange = { descricao = it },
                            label = { Text("Descrição") })

                        4 -> Input(
                            value = logradouro,
                            onValueChange = { logradouro = it },
                            label = { Text("Logradouro") })

                        5 -> Input(
                            value = numero,
                            onValueChange = { numero = it },
                            label = { Text("Número") })

                        6 -> Input(
                            value = complemento ?: "",
                            onValueChange = { complemento = it },
                            label = { Text("Complemento") })

                        7 -> Input(
                            value = cidade,
                            onValueChange = { cidade = it },
                            label = { Text("Cidade") })

                        8 -> Input(
                            value = estado,
                            onValueChange = { estado = it },
                            label = { Text("Estado") })
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Botao(
                    onClick = {
                        val barbeariaAtualizada = BarbeariaDTO(
                            nomeNegocio,
                            emailNegocio,
                            celularNegocio,
                            descricao,
                            cep,
                            logradouro,
                            numero.toInt(),
                            complemento,
                            cidade,
                            estado,
                            diaSemanas
                        )
                        CoroutineScope(Dispatchers.IO).launch {
                            val result = barbeariaViewModel.editarPerfil(barbeariaAtualizada)
                            if (result.isSuccessful) {
                                CoroutineScope(Dispatchers.Main).launch {
                                    Toast.makeText(context, "Perfil atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                                }
                                onDismiss() // Fecha o modal se a atualização for bem-sucedida
                                barbeariaBody?.let { barbeariaViewModel.obterBarbearia(isBarbeiro = true, idBarbearia = it.id) }

                            } else {
                                CoroutineScope(Dispatchers.Main).launch {
                                    Toast.makeText(context, "Falha ao realizar a alteração de perfil!", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    },
                    textButton = "Salvar",
                )
            }
        }
    )

    // Mostrar mensagens de erro
    if (errorMessage.value.isNotEmpty()) {
        Toast.makeText(LocalContext.current, errorMessage.value, Toast.LENGTH_SHORT).show()
    }
}


@Preview
@Composable
fun PreviewConfiguracoesSeuNegocio() {
    val usuarios = usuarios()
    ConfiguracoesSeuNegocio(navController = rememberNavController(), usuario = usuarios.first())
}
