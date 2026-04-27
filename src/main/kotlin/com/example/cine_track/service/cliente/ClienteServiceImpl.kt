package com.example.cine_track.service.cliente

import com.example.cine_track.controller.cliente.ClienteDTO
import com.example.cine_track.repository.ClienteRepository
import com.example.cine_track.controller.cliente.CriarClienteRequest
import com.example.cine_track.controller.cliente.EditarClienteRequest
import com.example.cine_track.exception.ConflictException
import com.example.cine_track.exception.NotFoundException
import com.example.cine_track.model.Cliente
import com.example.cine_track.model.ClienteStatus
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ClienteServiceImpl(
    private val clienteRepository: ClienteRepository
) : ClienteService {

    override fun criarCliente(request: CriarClienteRequest): ClienteDTO {
        val existeCliente = clienteRepository.existsByCpf(
            request.cpf
        )
        if (existeCliente) {
            throw ConflictException(
                "Já existe um cliente cadastrado com esse cpf"
            )

        }
        val cliente = Cliente(
            id = UUID.randomUUID(),
            nome = request.nome,
            cpf = request.cpf,
            email = request.email,
            status = ClienteStatus.ATIVO
        )
        val clienteSalvo = clienteRepository.save(cliente)
        return ClienteDTO.fromEntity(clienteSalvo)
    }

    override fun listarTodos(): List<ClienteDTO> {
        val clientesEncontrados = clienteRepository.findAll()
        return clientesEncontrados.map { ClienteDTO.fromEntity(it) }
    }

    override fun buscarPorId(id: UUID): ClienteDTO {
        val cliente = clienteRepository.findById(id).orElseThrow {
                NotFoundException("O cliente com id $id não foi encontrado")
            }
        return ClienteDTO.fromEntity(cliente)
    }

    override fun editarCliente(
        id: UUID,
        request: EditarClienteRequest
    ): ClienteDTO {

        val cliente = clienteRepository.findById(id)
            .orElseThrow{
                NotFoundException("Cliente com o id $id não encontrado"
                )
            }
        val clienteAtualizado = cliente.copy(
            nome = request.nome ?: cliente.nome,
            email = request.email ?: cliente.email
        )
        val clienteSalvo = clienteRepository.save(clienteAtualizado)
        return ClienteDTO.fromEntity(clienteSalvo)

    }
}