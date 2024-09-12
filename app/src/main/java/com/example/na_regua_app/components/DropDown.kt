package com.example.na_regua_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(
    actions: List<Pair<String, (String) -> Unit>>, // Lista de pares (texto, função)
    modifier: Modifier = Modifier,
    menuWidth: Dp
) {
    val selectedText = remember { mutableStateOf(actions.first().first) }
    val expanded = remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .background(Color.White)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded.value,
            onExpandedChange = { isExpanded ->
                expanded.value = isExpanded
            }
        ) {
            TextField(
                value = selectedText.value,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .rotate(if (expanded.value) 180f else 0f) // Rotaciona o ícone
                            .align(Alignment.Center) // Alinha verticalmente o ícone
                    )
                },
                modifier = Modifier
                    .menuAnchor()
                    .background(Color.Transparent)
                    .fillMaxWidth(), // Faz o TextField ocupar a largura máxima disponível
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = ORANGE_SECUNDARY,
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedTrailingIconColor = Color.Black,
                    unfocusedTrailingIconColor = ORANGE_SECUNDARY
                ),
                shape = RectangleShape
            )

            ExposedDropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier
                    .background(Color.White)
                    .width(menuWidth) // Define a largura do menu
            ) {
                actions.forEach { (text, action) ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = text,
                                color = Color.Black
                            )
                        },
                        onClick = {
                            selectedText.value = text
                            expanded.value = false
                            action(text) // Chama a função associada ao item selecionado
                        },
                        modifier = Modifier
                            .background(Color.White) // Define o fundo branco para cada item do menu
                    )
                }
            }
        }
    }
}
