import com.example.na_regua_app.data.model.NovoServico
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import com.example.na_regua_app.data.model.ServicoConsulta
import com.example.na_regua_app.data.repository.ServicoRepository
import com.example.na_regua_app.viewmodel.ServicoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Response

@ExperimentalCoroutinesApi
class ServicoViewModelTest {

    @get:Rule
    val coroutineTestRule = TestCoroutineRule()

    private lateinit var servicoRepository: ServicoRepository
    private lateinit var viewModel: ServicoViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(coroutineTestRule.testDispatcher)
        servicoRepository = mock(ServicoRepository::class.java)
        viewModel = ServicoViewModel(servicoRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        coroutineTestRule.testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun cadastroServico() = runBlockingTest {
        // Dados de entrada para o teste
        val novoServico = NovoServico(
            preco = 100.0,
            descricao = "Corte de cabelo",
            tipoServico = "Corte",
            tempoEstimado = 30,
            barbeirosEmails = listOf("barbeiro@exemplo.com")
        )

        val servicoConsulta = ServicoConsulta(
            1,
            preco = 100.0,
            descricao = "Corte de cabelo",
            tipoServico = "Corte",
            tempoEstimado = "0",
            status = true,
            barbeiros = setOf("a", "b")
        )

        // Simulando uma resposta bem-sucedida do repositório
        val mockResponse = Response.success(servicoConsulta)
        `when`(servicoRepository.cadastrarServico(novoServico)).thenReturn(mockResponse)

        // Chamando a função que estamos testando
        viewModel.criarServico(novoServico, onResult = { })

        // Esperando que o estadoServico seja atualizado para Success(true)
        val estadoServico = viewModel.servicos.first()

        // Verificando o estado retornado
        assertTrue(estadoServico.isNotEmpty())

        // Verificando se o repositório foi chamado corretamente
        verify(servicoRepository).cadastrarServico(novoServico)
    }
}

@ExperimentalCoroutinesApi
class TestCoroutineRule : TestWatcher() {
    val testDispatcher = TestCoroutineDispatcher()

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}