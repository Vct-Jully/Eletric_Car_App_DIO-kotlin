package com.example.eletric_car_dio.data

import com.example.eletric_car_dio.domain.Carro

object CarFactory {

    val list = listOf(
        Carro(
            id = 1,
            preco = "R$300.000,00",
            bateria = "300 kWh",
            recarga = "30 min",
            potencia = "200 cv",
            urlPhoto = "www.google.com.br"
        ),
        Carro(
            id = 2,
            preco = "R$250.000,00",
            bateria = "310 kWh",
            recarga = "50 min",
            potencia = "180 cv",
            urlPhoto = "www.google.com.br"

        ),
        Carro(
            id = 3,
            preco = "R$260.000,00",
            bateria = "315 kWh",
            recarga = "55 min",
            potencia = "190 cv",
            urlPhoto = "www.google.com.br"

        )

    )

    //VERBOS HTTP
    // - GET -> recuperar informações
    // - POST -> enviar informações para um servidor
    // - DELETE -> deletar um recurso
    // - PUT -> alterar uma entidade como um todo
    // - PATCH -> alterar um atributo da entidade
}