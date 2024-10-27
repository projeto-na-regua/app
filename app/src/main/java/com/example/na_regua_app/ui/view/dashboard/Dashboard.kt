@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.na_regua_app.ui.view.dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.data.model.Usuario
import com.example.na_regua_app.data.model.usuarios
import com.example.na_regua_app.ui.components.BottomBarCustom
import com.example.na_regua_app.ui.components.TopBarCustom
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(
    navController: NavController,
    usuario: Usuario
) {
    Scaffold(
        topBar = {
            TopBarCustom(navController, "Dashboard", true)
        },
        content = { paddingValues ->
            DashboardContent(paddingValues)
        },
        bottomBar = {
            BottomBarCustom(navController, LocalContext.current)
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashboardContent(paddingValues: PaddingValues) {
    val tabs = listOf(
        "Agendamentos",
        "Financeiro",
        "Gerenciamento"
    )
    var currentTabIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(paddingValues).fillMaxSize()
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
                modifier = Modifier.fillMaxWidth(),
                text = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = tab,
                            fontSize = 12.sp
                        )
                    }
                },
                selected = index == selectedTabIndex,
                onClick = { onTabSelected(index) },
                selectedContentColor = ORANGE_SECUNDARY,
                unselectedContentColor = Color.Gray,
                interactionSource = remember { MutableInteractionSource() }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TabContent(selectedTabIndex: Int) {
    when (selectedTabIndex) {
        0 -> AgendamentosContent()
        1 -> FinanceiroContent()
        2 -> GerenciamentoContent()
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun DashboardPreview() {
    val navController = rememberNavController()
    Dashboard(navController = navController, usuarios().first())
}
