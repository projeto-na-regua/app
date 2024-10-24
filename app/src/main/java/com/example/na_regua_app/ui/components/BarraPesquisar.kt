package com.example.na_regua_app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.na_regua_app.R
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraPesquisar(
    onSearch: (String) -> Unit,
    navController: NavController,
    nomeBarbearia: String
) {
    val textState = remember { mutableStateOf(TextFieldValue(nomeBarbearia)) }

    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF082031))
            .padding(horizontal = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.icone_lupa),
                contentDescription = "Ícone de lupa",
                modifier = Modifier
                    .size(28.dp)
                    .clickable {
                        if (textState.value.text.isNotEmpty()) {
                            val route = "listagemBarbearias/${textState.value.text}"
                            println("Navegando para: $route")
                            navController.navigate(route)
                        } else {
                            println("Por favor, preencha todos os campos antes de pesquisar.")
                        }
                    }
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                placeholder = {
                    Text(
                        text = "Pesquisar barbearias",
                        color = ORANGE_SECUNDARY,
                        fontSize = 15.sp,
                        letterSpacing = 1.sp
                    )
                },
                modifier = Modifier.weight(1f),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default, // Remove ImeAction personalizada
                keyboardActions = KeyboardActions.Default, // Remove a ação de "Enviar"
                colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.icone_filtro),
                contentDescription = "Ícone de filtro",
                modifier = Modifier
                    .size(28.dp)
                    .clickable { navController.navigate("buscarBarbearias") }
            )
        }
    }
}

