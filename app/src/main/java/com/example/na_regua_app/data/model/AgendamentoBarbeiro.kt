package com.example.na_regua_app.data.model

import android.annotation.SuppressLint
import java.time.LocalDateTime

data class AgendamentoBarbeiro(
    val id: Int,
    val nomeCliente: String,
    var imgCliente: String,
    val dataHora: LocalDateTime,
    var status: String,
    var tipoServico: String,
    var tempoEstimado: Int
)


@SuppressLint("NewApi")
fun criarListaAgendamentos(): List<AgendamentoBarbeiro> {
    return listOf(
        AgendamentoBarbeiro(
            id = 1,
            nomeCliente = "João Silva",
            imgCliente = "https://th.bing.com/th/id/R.569db13776d6ed10f68a325c0f49ac27?rik=b%2bDN16OLrmuGrw&pid=ImgRaw&r=0",
            dataHora = LocalDateTime.of(2024, 9, 10, 14, 30),
            status = "Pendente",
            tipoServico = "Corte de Cabelo",
            tempoEstimado = 30
        ),
        AgendamentoBarbeiro(
            id = 2,
            nomeCliente = "Carlos Souza",
            imgCliente = "https://i.pinimg.com/originals/1f/23/2b/1f232b076f85aa1a5936a325824e28b4.jpg",
            dataHora = LocalDateTime.of(2024, 9, 10, 16, 0),
            status = "Pendente",
            tipoServico = "Barba",
            tempoEstimado = 45
        ),
        AgendamentoBarbeiro(
            id = 3,
            nomeCliente = "Pedro Costa",
            imgCliente = "https://asklegally.com/wp-content/uploads/2023/03/handsome-man-black-suit-with-white-shirt-posing-attractive-guy-with-fashion-hairstyle-confident-man-with-short-beard-adult-boy-with-brown-hair-full-portrait-1-1.png",
            dataHora = LocalDateTime.of(2024, 9, 10, 17, 0),
            status = "Agendado",
            tipoServico = "Corte e Barba",
            tempoEstimado = 60
        ),
        AgendamentoBarbeiro(
            id = 4,
            nomeCliente = "Lucas Lima",
            imgCliente = "https://img.freepik.com/free-photo/handsome-man-white-shirt-holds-black-suit-posing-wall-attractive-guy-with-fashion-hairstyle-confident-man-with-short-beard-adult-boy-with-brown-hair_186202-8533.jpg?w=360&t=st=1710037789~exp=1710038389~hmac=c6a62e061c6778b844fdef5b4d286637106d713a6ffa5a0729579e5801fd0807",
            dataHora = LocalDateTime.of(2024, 9, 11, 10, 0),
            status = "Cancelado",
            tipoServico = "Corte de Cabelo",
            tempoEstimado = 40
        ),
        AgendamentoBarbeiro(
            id = 5,
            nomeCliente = "Marcos Alves",
            imgCliente = "https://www.jkrock.net/wp-content/uploads/elementor/thumbs/portrait-of-a-handsome-young-man-P69ZP2W-q35m9at9dlrwojw3qpsu3uph241dlgfgmzabblmga6.jpg",
            dataHora = LocalDateTime.of(2024, 9, 11, 12, 30),
            status = "Concluído",
            tipoServico = "Corte e Hidratação",
            tempoEstimado = 50
        )
    )
}
