import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.R
import com.example.na_regua_app.data.model.Usuario
import com.example.na_regua_app.data.model.usuarios
import com.example.na_regua_app.ui.components.BotaoComIcone
import com.example.na_regua_app.ui.components.BottomBarCustom
import com.example.na_regua_app.ui.components.TopBarCustom
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.ui.theme.textSection
import com.example.na_regua_app.ui.theme.titleSection
import com.example.na_regua_app.ui.theme.titleSectionBold
import com.example.na_regua_app.viewmodel.PerfilUsuarioViewModel
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.material3.Surface
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.ui.platform.LocalContext
import com.example.na_regua_app.data.model.UsuarioDTOUpdate
import com.example.na_regua_app.ui.components.Input
import com.example.na_regua_app.ui.components.Botao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ConfiguracoesInformacoesPessoais(
    navController: NavController,
    usuario: Usuario,
    usuarioViewModel: PerfilUsuarioViewModel = koinViewModel(),
) {
    var showEditDialog by remember { mutableStateOf(false) }

    val usuarioBody by usuarioViewModel.usuario.collectAsState()



    Scaffold(
        topBar = {
            TopBarCustom(navController, "Configurações", false, false, true)
        },
        content = { paddingValues ->
            if (usuarioBody != null) {

                var nome by remember { mutableStateOf(usuarioBody!!.nome) }
                var email by remember { mutableStateOf(usuarioBody!!.email) }
                val senha by remember { mutableStateOf(usuarioBody!!.senha) }
                val celular by remember { mutableStateOf(usuarioBody!!.celular) }
                val cep by remember { mutableStateOf(usuarioBody!!.cep) }
                val logradouro by remember { mutableStateOf(usuarioBody!!.logradouro) }
                val numero by remember { mutableStateOf(usuarioBody!!.numero) }
                val complemento by remember { mutableStateOf(usuarioBody!!.complemento) }
                val cidade by remember { mutableStateOf(usuarioBody!!.cidade) }
                val estado by remember { mutableStateOf(usuarioBody!!.estado) }
                var username by remember { mutableStateOf(usuarioBody!!.username) }

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
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                ImageWithEditIcon(
                                    painter = painterResource(id = R.drawable.foto_exemplo),
                                    onClick = {},
                                    shape = CircleShape
                                )

                                Column {
                                    Text(text = "Formatos permitidos", style = titleSection)

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(text = "JPG, PNG e JPEG", style = textSection)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            Text(text = "Informações Pessoais", style = titleSectionBold)

                            Spacer(modifier = Modifier.height(24.dp))

                            Text(text = "Apelido", style = titleSection)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = username, style = textSection)

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(text = "Nome", style = titleSection)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = nome, style = textSection)

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(text = "E-mail", style = titleSection)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = email, style = textSection)

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(text = "Senha", style = titleSection)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = senha, style = textSection)

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(text = "Telefone", style = titleSection)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = celular, style = textSection)

                            Spacer(modifier = Modifier.height(32.dp))

                            Text(text = "Informações de Endereço", style = titleSectionBold)

                            Spacer(modifier = Modifier.height(24.dp))

                            Text(text = "Endereço", style = titleSection)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "$logradouro, $numero - $complemento - $cep",
                                style = textSection
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(text = "Cidade/Estado", style = titleSection)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "$cidade - $estado",
                                style = textSection
                            )

                            Spacer(modifier = Modifier.height(32.dp))

                            Button(
                                onClick = {
                                    navController.navigate("deleteaccount")
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Transparent,
                                    contentColor = Color(0xFFCC2828)
                                ),
                                border = BorderStroke(1.dp, Color(0xFFCC2828)),
                                shape = RoundedCornerShape(12.dp),
                                contentPadding = PaddingValues(16.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Excluir conta",
                                        style = androidx.compose.ui.text.TextStyle(
                                            color = Color(0xFFCC2828),
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )

                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowRight,
                                        contentDescription = null
                                    )
                                }
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
                                onClick = { showEditDialog = true }, // Mostra o modal ao clicar
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
            BottomBarCustom(navController, usuario)
        }
    )

    // Modal para edição de informações
    if (showEditDialog) {
        EditarInformacoesDialog(
            usuario = usuario,
            onDismiss = { showEditDialog = false },
        )
    }
}

@Composable
fun EditarInformacoesDialog(
    usuario: Usuario,
    onDismiss: () -> Unit,
    usuarioViewModel: PerfilUsuarioViewModel = koinViewModel() // ViewModel
) {

    val usuarioBody by usuarioViewModel.usuario.collectAsState()

    var nome by remember { mutableStateOf(usuarioBody!!.nome) }
    var email by remember { mutableStateOf(usuarioBody!!.email) }
    var celular by remember { mutableStateOf(usuarioBody!!.celular) }
    var cep by remember { mutableStateOf(usuarioBody!!.cep) }
    var logradouro by remember { mutableStateOf(usuarioBody!!.logradouro) }
    var numero by remember { mutableStateOf(usuarioBody!!.numero.toString()) }
    var complemento by remember { mutableStateOf(usuarioBody!!.complemento) }
    var cidade by remember { mutableStateOf(usuarioBody!!.cidade) }
    var estado by remember { mutableStateOf(usuarioBody!!.estado) }
    var username by remember { mutableStateOf(usuarioBody!!.username) }

    val context = LocalContext.current

    // Estado para mostrar mensagens de erro ou sucesso
    val errorMessage = remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Editar Informações") },
        text = {
            LazyColumn {
                items(8) { index ->
                    when (index) {
                        0 -> Input(value = nome, onValueChange = { nome = it }, label = { Text("Nome") })
                        1 -> Input(value = email, onValueChange = { email = it }, label = { Text("E-mail") })
                        2 -> Input(value = celular, onValueChange = { celular = it }, label = { Text("Telefone") })
                        3 -> Input(value = logradouro, onValueChange = { logradouro = it }, label = { Text("Logradouro") })
                        4 -> Input(value = numero, onValueChange = { numero = it }, label = { Text("Número") })
                        5 -> Input(value = complemento, onValueChange = { complemento = it }, label = { Text("Complemento") })
                        6 -> Input(value = cidade, onValueChange = { cidade = it }, label = { Text("Cidade") })
                        7 -> Input(value = estado, onValueChange = { estado = it }, label = { Text("Estado") })
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
                        val usuarioAtualizado = UsuarioDTOUpdate(

                            nome,
                            email,
                            celular,
                            cep,
                            logradouro,
                            numero.toInt(),
                            complemento,
                            cidade,
                            estado,
                            username,
                        )
                        // Chama a função de edição de perfil
                        CoroutineScope(Dispatchers.IO).launch {
                            val result = usuarioViewModel.editarPerfil(usuarioAtualizado)
                            if (result.isSuccessful) {
//                                Toast.makeText(context, "Perfil atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                                onDismiss() // Fecha o modal se a atualização for bem-sucedida
                            }else{
//                                Toast.makeText(context, "Falha ao realizar a alteração de perfil!", Toast.LENGTH_SHORT).show()
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
fun ConfiguracoesInformacoesPessoaisPreview() {
    val usuarios = usuarios()
    ConfiguracoesInformacoesPessoais(navController = rememberNavController(), usuarios.first())
}

@Composable
fun ImageWithEditIcon(
    painter: Painter,
    onClick: () -> Unit,
    width: Int = 100,
    height: Int = 100,
    shape: Shape,
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.clickable { onClick() }
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width.dp, height.dp)
                .border(
                    BorderStroke(2.dp, Color(0xFFCBD5E0)),
                    shape = shape
                )
        )

        Box(
            modifier = Modifier
                .offset(x = (-40).dp)
                .background(BLUE_PRIMARY, CircleShape)
                .size(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                tint = ORANGE_SECUNDARY,
            )
        }
    }
}
