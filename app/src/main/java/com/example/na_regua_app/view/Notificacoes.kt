package com.example.na_regua_app.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.components.BottomBarCustom
import com.example.na_regua_app.components.TopBarCustom
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Notificacoes(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopBarCustom(navController, "Notificações", false)
        },
        content = { paddingValues ->
            NotificacoesContent(
                paddingValues = paddingValues
            )
        },
        bottomBar = {
            BottomBarCustom(navController)
        }
    )
}

@Composable
fun NotificacoesContent(paddingValues: PaddingValues) {

    val tabs = listOf("Geral", "Comunidade", "Sua barbearia")
    var currentTabIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(paddingValues)
    ) {
        TabLayout(
            tabs = tabs,
            selectedTabIndex = currentTabIndex,
            onTabSelected = { index ->
                currentTabIndex = index
            }
        )
        TabContent(selectedTabIndex = currentTabIndex)
    }

}

@Composable
fun TabLayout(
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.Transparent,
        contentColor = Color.Gray,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                color = ORANGE_SECUNDARY
            )
        },
        divider = { /* Não desenha nada */ },
        modifier = Modifier.border(0.dp, Color.Transparent)
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                text = { Text(tab) },
                selected = index == selectedTabIndex,
                onClick = { onTabSelected(index) },
                selectedContentColor = ORANGE_SECUNDARY,
                unselectedContentColor = Color.Gray,
                interactionSource = remember { MutableInteractionSource() }
            )
        }
    }
}

@Composable
fun TabContent(selectedTabIndex: Int) {
    when (selectedTabIndex) {
        0 -> GeralContent()
        1 -> ComunidadeContent()
        2 -> SuaBarbeariaContent()
    }
}

@Composable
fun GeralContent() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
        ){
        Text(
            text = "Nenhuma notificação",
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun ComunidadeContent() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Nenhuma notificação",
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun SuaBarbeariaContent() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Nenhuma notificação",
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NotificacoesPreview() {
    val navController = rememberNavController()
    Notificacoes(navController = navController)
}
