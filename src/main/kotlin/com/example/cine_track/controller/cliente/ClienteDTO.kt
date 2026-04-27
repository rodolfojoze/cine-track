package com.example.cine_track.controller.cliente

import com.example.cine_track.model.Cliente
import com.example.cine_track.model.ClienteStatus
import java.util.UUID

class ClienteDTO (
    val id: UUID,
    val nome: String,
    val cpf: String,
    val email: String,
    val status: ClienteStatus
) {
    companion object {
        fun fromEntity(cliente: Cliente): ClienteDTO {
            return ClienteDTO(
                id = cliente.id,
                nome = cliente.nome,
                cpf = cliente.cpf,
                email = cliente.email,
                status = cliente.status
            )
        }
    }
}