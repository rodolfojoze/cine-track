package com.example.cine_track.controller.cliente

import com.example.cine_track.service.cliente.ClienteService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/clientes")
class ClienteController (
    private val service: ClienteService
) {

    @PostMapping
    fun criarCliente(
        @RequestBody request: CriarClienteRequest
    ): ResponseEntity<ClienteDTO> {

        val clienteCriado = service.criarCliente(request)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(clienteCriado)
    }

    @GetMapping
    fun listarClientes(): ResponseEntity<List<ClienteDTO>>{
        return ResponseEntity.ok(service.listarTodos())
    }

    @GetMapping("/{id}")
    fun clientePorId(@PathVariable id: UUID) =
        service.buscarPorId(id)

    @PutMapping("/{id}")
    fun editarCliente(
        @PathVariable id: UUID,
        @RequestBody request: EditarClienteRequest
    ): ResponseEntity<ClienteDTO> {

        val clienteEditado = service.editarCliente(id, request)

        return ResponseEntity.ok(clienteEditado)
    }




}
