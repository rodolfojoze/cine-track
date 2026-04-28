package com.example.cine_track.controller.cliente

import org.hibernate.validator.constraints.br.CPF


class CriarClienteRequest (
    val nome: String,

    @field:CPF(message = "CPF inválido")
    val cpf: String,

    val email: String
)