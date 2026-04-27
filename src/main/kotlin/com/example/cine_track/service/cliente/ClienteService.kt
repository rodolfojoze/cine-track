package com.example.cine_track.service.cliente

import com.example.cine_track.controller.cliente.CriarClienteRequest
import com.example.cine_track.controller.cliente.ClienteDTO
import com.example.cine_track.controller.cliente.EditarClienteRequest
import java.util.UUID

interface ClienteService {
    fun criarCliente(cliente: CriarClienteRequest): ClienteDTO
    fun listarTodos(): List<ClienteDTO>
    fun buscarPorId(id: UUID): ClienteDTO
    fun editarCliente(
        id: UUID,
        request: EditarClienteRequest
    ): ClienteDTO
}