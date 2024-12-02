import com.example.na_regua_app.data.model.BarbeiroConsulta
import com.example.na_regua_app.data.model.NovoBarbeiro
import com.example.na_regua_app.data.repository.FuncionarioRepository
import com.example.na_regua_app.viewmodel.FuncionarioViewModel
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class FuncionarioViewModelTest {

    @Mock
    private lateinit var funcionarioRepository: FuncionarioRepository

    private lateinit var viewModel: FuncionarioViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = FuncionarioViewModel(funcionarioRepository)
    }

    @Test
    fun `deve cadastrar funcionario com sucesso`() = runBlockingTest {
        // Criando um objeto NovoBarbeiro para o teste
        val novoBarbeiro = NovoBarbeiro("Barbeiro 1", "barbeiro1@email.com", "senha123", "999999999")

        // Simulando a resposta de sucesso ao cadastrar o barbeiro
        Mockito.`when`(funcionarioRepository.cadastrarFuncionario(novoBarbeiro)).thenReturn(Response.success(
            BarbeiroConsulta(1, "Barbeiro 1", "barbeiro@gmail.com", "a", "Corte" )
        ))

        // Variável para capturar o resultado do callback
        var result = false

        // Chamando o método cadastrarFuncionario e passando o callback
        viewModel.cadastrarFuncionario(novoBarbeiro) { success ->
            result = success
        }

        // Verificando que o resultado foi verdadeiro, indicando sucesso no cadastro
        assertTrue(result)
    }

    @Test
    fun `deve falhar ao cadastrar funcionario`() = runBlockingTest {
        // Criando um objeto NovoBarbeiro para o teste
        val novoBarbeiro = NovoBarbeiro("Barbeiro 1", "barbeiro1@email.com", "senha123", "999999999")

        // Simulando a resposta de erro ao cadastrar o barbeiro
        Mockito.`when`(funcionarioRepository.cadastrarFuncionario(novoBarbeiro)).thenReturn(Response.error(500, "Erro ao cadastrar".toResponseBody()))

        // Variável para capturar o resultado do callback
        var result = false

        // Chamando o método cadastrarFuncionario e passando o callback
        viewModel.cadastrarFuncionario(novoBarbeiro) { success ->
            result = success
        }

        // Verificando que o resultado foi falso, indicando falha no cadastro
        assertFalse(result)
    }
}
