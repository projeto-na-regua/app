import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.R
import com.example.na_regua_app.data.model.Usuario
import com.example.na_regua_app.data.model.usuarios
import com.example.na_regua_app.ui.components.BottomBarCustom
import com.example.na_regua_app.ui.components.TopBarCustom
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import kotlin.math.roundToInt

data class Post(
    val imageUrl: Int,
    val username: String,
    val postMessage: String,
    val initialIsFollowing: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Comunidade(
    navController: NavController,
    usuario: Usuario
) {
    var posts by remember {
        mutableStateOf(
            listOf(
                Post(R.drawable.foto_exemplo, "@barbeiro_ofc", "Fiz um novo corte e meu cliente ficou muito feliz!! :)", initialIsFollowing = false),
                Post(R.drawable.foto_exemplo, "@outro_usuario", "O novo estilo está na moda!", initialIsFollowing = true),
            )
        )
    }

    // Estado do modal
    val modalOpen = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBarCustom(navController, "Comunidade", true)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                InputPostField(
                    onAddPost = { imageUrl, username, postMessage, isFollowing ->
                        addPost(imageUrl, username, postMessage, isFollowing, posts) { updatedPosts ->
                            posts = updatedPosts
                        }
                    }
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp)
                ) {
                    items(posts) { post ->
                        UserPost(
                            imageUrl = post.imageUrl,
                            username = post.username,
                            postMessage = post.postMessage,
                            initialIsFollowing = post.initialIsFollowing,
                            onCommentClick = {
                                modalOpen.value = true // Abre o modal ao clicar no comentário
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        },
        bottomBar = {
            BottomBarCustom(navController, usuario)
        }
    )

    // Renderiza o modal fora do Scaffold
    if (modalOpen.value) {
        DraggableModal(
            onDismiss = { modalOpen.value = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DraggableModal(onDismiss: () -> Unit) {
    var offsetY by remember { mutableStateOf(0f) }
    var postMessage by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x99000000))
            .clickable { onDismiss() }, // Fecha o modal ao clicar fora
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset { IntOffset(0, offsetY.roundToInt()) }
                .draggable(
                    orientation = Orientation.Vertical,
                    state = rememberDraggableState { delta ->
                        if (delta > 0) {
                            offsetY += delta
                            if (offsetY > 600) {
                                onDismiss()
                            }
                        }
                    }
                )
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                .background(Color.White)
                .height(600.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Divider(
                color = Color(0xFFCBD5E0),
                thickness = 1.dp,
                modifier = Modifier
                    .padding(top = 32.dp, bottom = 16.dp)
                    .width(40.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Divider(
                    color = Color(0xFFCBD5E0),
                    thickness = 1.dp,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Comentários", color = Color(0xFF082031), fontSize = 20.sp, fontWeight = FontWeight.Bold)

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    items(3) {
                        UserPost(
                            imageUrl = R.drawable.foto_exemplo,
                            username = "@usuario",
                            postMessage = "Comentário de exemplo",
                            initialIsFollowing = false
                        ) {

                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
            
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Box(modifier = Modifier
                        .size(50.dp)
                        .border(1.dp, Color(0xFFCBD5E0), RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = "Menu",
                            tint = Color(0xFFCBD5E0)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    OutlinedTextField(
                        value = postMessage,
                        onValueChange = { postMessage = it },
                        modifier = Modifier
                            .width(230.dp)
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = BLUE_PRIMARY,
                            unfocusedBorderColor = BLUE_PRIMARY,
                            focusedLabelColor = BLUE_PRIMARY,
                            unfocusedLabelColor = BLUE_PRIMARY
                        )
                    )
                }

                Box(modifier = Modifier
                    .size(50.dp)
                    .border(1.dp, Color(0xFFCBD5E0), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Menu",
                        tint = Color(0xFFCBD5E0)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputPostField(onAddPost: (Int, String, String, Boolean) -> Unit) {
    var postMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        OutlinedTextField(
            value = postMessage,
            onValueChange = { postMessage = it },
            label = { Text("Escreva seu post") },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = BLUE_PRIMARY,
                unfocusedBorderColor = BLUE_PRIMARY,
                focusedLabelColor = BLUE_PRIMARY,
                unfocusedLabelColor = BLUE_PRIMARY
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                onAddPost(R.drawable.foto_exemplo, "@usuario", postMessage, false)
                postMessage = ""
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = BLUE_PRIMARY
            ),
            shape = RoundedCornerShape(12.dp),
        ) {
            Text(
                text = "Adicionar post",
                color = ORANGE_SECUNDARY,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.sp
            )
        }
    }
}

@Composable
fun UserPost(
    imageUrl: Int,
    username: String,
    postMessage: String,
    initialIsFollowing: Boolean,
    onCommentClick: () -> Unit
) {
    var like by remember { mutableStateOf(false) }
    var isFollowing by remember { mutableStateOf(initialIsFollowing) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 80.dp)
            .border(
                border = BorderStroke(1.dp, Color(0xFFCBD5E0)),
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = imageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(75.dp)
                .border(
                    BorderStroke(2.dp, Color(0xFFCBD5E0)),
                    shape = CircleShape
                )
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = username, color = Color(0xFF082031))

                Text(
                    text = if (isFollowing) "Seguindo" else "Seguir +",
                    color = Color(0xFFE3A74F),
                    modifier = Modifier.clickable {
                        isFollowing = !isFollowing
                    }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Column {
                Text(text = postMessage, color = Color(0xFF082031))
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.comment),
                    contentDescription = "Comment",
                    tint = Color(0xFFCBD5E0),
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            onCommentClick()
                        }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = if (like) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Like",
                    tint = if (like) Color(0xFFE3A74F) else Color(0xFFCBD5E0),
                    modifier = Modifier
                        .size(26.dp)
                        .clickable {
                            like = !like
                        }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComunidadePreviewDisplay() {
    val navController = rememberNavController()
    val usuarios = usuarios()
    Comunidade(navController,usuarios.first())
}

fun addPost(
    imageUrl: Int,
    username: String,
    postMessage: String,
    isFollowing: Boolean,
    currentPosts: List<Post>,
    onPostAdded: (List<Post>) -> Unit
) {
    val newPost = Post(imageUrl, username, postMessage, isFollowing)
    val updatedPosts = currentPosts.toMutableList()
    updatedPosts.add(0, newPost)
    onPostAdded(updatedPosts)
}
