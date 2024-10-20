package com.example.na_regua_app.data.model

data class Usuario(
    val nome: String,
    val email: String,
    val senha: String,
    val celular: String,
    var imgPerfil: String,
    val cep: String,
    val logradouro: String,
    val numero: Int,
    val complemento: String,
    val cidade: String,
    val estado: String,
    val dtype: String,
)

fun usuarios(): List<Usuario> {
    return listOf(
        Usuario(
            nome = "Melissa Silva",
            email = "melissa@gmail.com",
            senha = "senha123",
            celular = "(11) 98765-4321",
            imgPerfil = "https://example.com/img/melissa.jpg",
            cep = "12345-678",
            logradouro = "Rua das Flores",
            numero = 123,
            complemento = "Apto 101",
            cidade = "São Paulo",
            estado = "SP",
            dtype = "Barbeiro"
        ),
        Usuario(
            nome = "Vitor Souza",
            email = "vitor@gmail.com",
            senha = "senha456",
            celular = "(21) 99876-5432",
            imgPerfil = "https://example.com/img/vitor.jpg",
            cep = "87654-321",
            logradouro = "Avenida Central",
            numero = 456,
            complemento = "Casa",
            cidade = "Rio de Janeiro",
            estado = "RJ",
            dtype = "Cliente"
        )
    )
}
