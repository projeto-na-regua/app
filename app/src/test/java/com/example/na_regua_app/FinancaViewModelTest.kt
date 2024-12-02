import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.na_regua_app.data.model.FinancaCriacao
import com.example.na_regua_app.data.repository.FinancaRepository
import com.example.na_regua_app.viewmodel.FinancaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import retrofit2.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class FinancaViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var financaRepository: FinancaRepository

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun createViewModel(): FinancaViewModel {
        return FinancaViewModel(financaRepository)
    }

    @Test
    fun `test lancarValor sucesso`() = runTest {
        val financaCriacao = FinancaCriacao(2000.0, despesa = false)

        Mockito.`when`(financaRepository.lancarValor(financaCriacao))
            .thenReturn(Response.success(null))

        val viewModel = createViewModel()

        var result: Boolean? = null
        viewModel.lancarValor(financaCriacao) { success ->
            result = success
        }

        advanceUntilIdle()

        assertTrue(result == true)
    }

    @Test
    fun `test lancarValor erro`() = runTest {
        val financaCriacao = FinancaCriacao(2000.0, despesa = false)

        Mockito.`when`(financaRepository.lancarValor(financaCriacao))
            .thenReturn(Response.error(400, "Erro ao lançar".toResponseBody(null)))

        val viewModel = createViewModel()

        var result = true

        viewModel.lancarValor(financaCriacao) { success ->
            result = success
        }

        advanceUntilIdle()

        assertFalse(result === true)
    }
}
