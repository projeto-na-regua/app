package com.example.na_regua_app.data.di

import com.example.na_regua_app.data.api.AgendamentoService
import com.example.na_regua_app.data.api.BarbeariaService
import com.example.na_regua_app.data.api.FuncionarioService
import com.example.na_regua_app.data.api.PesquisaService
import com.example.na_regua_app.data.api.Rest
import com.example.na_regua_app.data.api.ServicoService
import com.example.na_regua_app.data.api.UsuarioService
import com.example.na_regua_app.data.repository.AgendamentoRepository
import com.example.na_regua_app.data.repository.AgendamentoRepositoryImpl
import com.example.na_regua_app.data.repository.BarbeariaRepository
import com.example.na_regua_app.data.repository.BarbeariaRepositoryImpl
import com.example.na_regua_app.data.repository.FuncionarioRepository
import com.example.na_regua_app.data.repository.FuncionarioRepositoryImpl
import com.example.na_regua_app.data.repository.PesquisaRepository
import com.example.na_regua_app.data.repository.PesquisaRepositoryImpl
import com.example.na_regua_app.data.repository.ServicoRepository
import com.example.na_regua_app.data.repository.ServicoRepositoryImpl
import com.example.na_regua_app.data.repository.UsuarioRepository
import com.example.na_regua_app.data.repository.UsuarioRepositoryImpl
import com.example.na_regua_app.data.repository.UsuarioRepositoryLocalImpl
import com.example.na_regua_app.ui.view.Login
import com.example.na_regua_app.viewmodel.AgendamentoViewModel
import com.example.na_regua_app.viewmodel.CadastroBarbeariaViewModel
import com.example.na_regua_app.viewmodel.CadastroViewModel
import com.example.na_regua_app.viewmodel.LoginViewModel
import com.example.na_regua_app.viewmodel.FuncionarioViewModel
import com.example.na_regua_app.viewmodel.PerfilBarbeariaViewModel
import com.example.na_regua_app.viewmodel.PerfilUsuarioViewModel
import com.example.na_regua_app.viewmodel.PesquisaViewModel
import com.example.na_regua_app.viewmodel.ServicoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule= module {

    // Services
    single<UsuarioService> { Rest.usuarioService }
    single<BarbeariaService> { Rest.barbeariaService }
    single<ServicoService> { Rest.servicoService }
    single<FuncionarioService> { Rest.funcionarioService }
    single<PesquisaService> { Rest.pesquisaService }
    single<AgendamentoService> { Rest.agendamentoService }

    // Repositories
    single<UsuarioRepository> {
        // Descomentar para produção vvv:
         UsuarioRepositoryImpl(get())
        // Descomentar para teste mock vvv:
        // UsuarioRepositoryLocalImpl()
    }

    single<BarbeariaRepository> { BarbeariaRepositoryImpl(get()) }
    single<ServicoRepository> { ServicoRepositoryImpl(get()) }
    single<FuncionarioRepository> { FuncionarioRepositoryImpl(get()) }
    single<PesquisaRepository> { PesquisaRepositoryImpl(get()) }
    single<AgendamentoRepository> { AgendamentoRepositoryImpl(get()) }

    // ViewModels
    viewModel<AgendamentoViewModel> { AgendamentoViewModel(get()) }
    viewModel<PesquisaViewModel> { PesquisaViewModel(get()) }
    viewModel<LoginViewModel> { LoginViewModel(get())}
    viewModel<CadastroBarbeariaViewModel> { CadastroBarbeariaViewModel(get())}
    viewModel<CadastroViewModel> { CadastroViewModel(get()) }
    viewModel<PerfilUsuarioViewModel> { PerfilUsuarioViewModel(get()) }
    viewModel<PerfilBarbeariaViewModel> { PerfilBarbeariaViewModel(get()) }
    viewModel<ServicoViewModel> { ServicoViewModel(get()) }
    viewModel<FuncionarioViewModel> { FuncionarioViewModel(get()) }
}