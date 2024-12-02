import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.na_regua_app.data.model.HorarioDisponivel
import com.example.na_regua_app.data.repository.AgendamentoRepository
import com.example.na_regua_app.viewmodel.AgendamentoViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class AgendamentoViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    val repository = mock<AgendamentoRepository>()
    private lateinit var viewModel: AgendamentoViewModel

    @Before
    fun setup() {
        // Set Dispatchers.Main to the test dispatcher
        Dispatchers.setMain(testDispatcher)

        // Inicializa o viewModel
        viewModel = AgendamentoViewModel(repository)
    }

    @Test
    fun listarHorariosDisponiveis() = runTest {
        // Dados simulados
        val mockResponse = listOf(
            HorarioDisponivel(idBarbeiro = 1, idServico = 1, hora = "08:00"),
            HorarioDisponivel(idBarbeiro = 1, idServico = 2, hora = "10:00")
        )

        // Criar uma Response simulada
        val response = Response.success(mockResponse)

        // Mock do repositório retornando a Response simulada
        `when`(repository.listarHorariosDisponiveis(anyInt(), anyInt(), anyInt(), anyString()))
            .thenReturn(response)

        // Executar o método
        viewModel.listarHorariosDisponiveis(1, 1, 1, "01/12/2024")


        // Coletar o StateFlow
        val horarios = viewModel.horarios.first()

        // Verificar se o resultado é o esperado
        assertTrue("Horários retornados não correspondem", horarios != mockResponse)
    }

}

