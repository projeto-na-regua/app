import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.na_regua_app.viewmodel.CadastroViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock

class CadastroViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CadastroViewModel

    @Before
    fun setup() {
        viewModel = CadastroViewModel(usuarioRepository = mock())
    }

    @Test
    fun `atualizarNome deve alterar o valor do nome`() {
        // Definir o novo nome
        val novoNome = "João da Silva"

        // Chamar a função
        viewModel.atualizarNome(novoNome)

        // Verificar se o nome foi alterado
        assertEquals(novoNome, viewModel.nome.value)
    }

    @Test
    fun `atualizarEmail deve alterar o valor do email`() {
        // Definir o novo email
        val novoEmail = "joao.silva@example.com"

        // Chamar a função
        viewModel.atualizarEmail(novoEmail)

        // Verificar se o email foi alterado
        assertEquals(novoEmail, viewModel.email.value)
    }

    @Test
    fun `atualizarSenha deve alterar o valor da senha`() {
        // Definir a nova senha
        val novaSenha = "senha1234"

        // Chamar a função
        viewModel.atualizarSenha(novaSenha)

        // Verificar se a senha foi alterada
        assertEquals(novaSenha, viewModel.senha.value)
    }

    @Test
    fun `atualizarCelular deve alterar o valor do celular`() {
        // Definir o novo celular
        val novoCelular = "11987654321"

        // Chamar a função
        viewModel.atualizarCelular(novoCelular)

        // Verificar se o celular foi alterado
        assertEquals(novoCelular, viewModel.celular.value)
    }
}
