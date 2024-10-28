package com.example.na_regua_app.ui.components

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.na_regua_app.data.model.AgendamentoBarbeiro
import com.example.na_regua_app.data.model.ItemMenuDropDown
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.view.dashboard.actions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(
    agendamentoBarbeiro: AgendamentoBarbeiro? = null,
    items: List<ItemMenuDropDown>,
    modifier: Modifier = Modifier,
    menuWidth: Dp = 100.dp,
    tamFont: TextUnit = 16.sp,
    tamIcon: Dp = 24.dp,
    selectedItemPosition: ItemMenuDropDown?, // Verifique se este parâmetro existe
    onItemSelected: (ItemMenuDropDown) -> Unit
) {
    val selectedItem = remember {
        mutableStateOf(selectedItemPosition ?: items.first())
    }
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
                value = selectedItem.value.name, // Exibe o nome do item selecionado
                onValueChange = {},
                readOnly = true,
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontSize = tamFont,
                    fontWeight = FontWeight.Bold
                ),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        modifier = Modifier
                            .width(tamIcon ?: 50.dp)
                            .rotate(if (expanded.value) 180f else 0f) // Rotaciona o ícone
                            .align(Alignment.Center) // Alinha verticalmente o ícone
                    )
                },
                modifier = Modifier
                    .menuAnchor().padding(0.dp)
                    .background(Color.Transparent)
                    .fillMaxWidth(), // Faz o TextField ocupar a largura máxima disponível
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedTextColor = selectedItem.value.colorAfter ?: BLUE_PRIMARY,
                    unfocusedTextColor = selectedItem.value.colorBefore ?: BLUE_PRIMARY, // Cor antes do clique
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedTrailingIconColor = selectedItem.value.colorAfter ?: BLUE_PRIMARY,
                    unfocusedTrailingIconColor = selectedItem.value.colorBefore ?: BLUE_PRIMARY,
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
                items.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(text = item.name, color = if (selectedItem.value == item) {
                                    item.colorAfter ?: BLUE_PRIMARY // Cor após o clique
                                } else {
                                    item.colorAfter ?: BLUE_PRIMARY// Cor antes do clique
                                }, fontSize = tamFont)
                        },
                        onClick = {
                            // Atualize o item selecionado
                            selectedItem.value = item
                            // Se o nameAfter não for nulo, use-o; caso contrário, use o name original
                            selectedItem.value = selectedItem.value.copy(name = item.nameAfter ?: item.name)
                            expanded.value = false
                            if (agendamentoBarbeiro != null){
                                actions(agendamentoBarbeiro, item.name)
                            }
                            item.action?.let { it() }
                        },
                        modifier = Modifier
                            .background(Color.White) // Define o fundo branco para cada item do menu
                    )
                }
            }
        }
    }
}


