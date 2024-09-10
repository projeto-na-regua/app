
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.na_regua_app.components.BottomBarCustom
import com.example.na_regua_app.components.TopBarCustom


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Comunidade(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopBarCustom(navController, "Comunidade",  true)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Text(text = "Welcome to the Home Screen")

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { /* Handle button click */ }) {
                    Text("Click Me")
                }
            }
        },
        bottomBar = {
            BottomBarCustom(navController)
        }
    )
}