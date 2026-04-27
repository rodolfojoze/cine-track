package com.example.cine_track.service.historico

import com.example.cine_track.controller.historico.AlugarFilmeRequest
import com.example.cine_track.repository.ClienteRepository
import com.example.cine_track.repository.FilmeRepository
import com.example.cine_track.repository.HistoricoRepository
import com.example.cine_track.controller.historico.HistoricoDTO
import com.example.cine_track.exception.BadRequestException
import com.example.cine_track.exception.NotFoundException
import com.example.cine_track.model.FilmeStatus
import com.example.cine_track.model.Historico
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class HistoricoServiceImpl(
    private val historicoRepository: HistoricoRepository,
    private val clienteRepository: ClienteRepository,
    private val filmeRepository: FilmeRepository,
) : HistoricoService {

    override fun alugarFilme(request: AlugarFilmeRequest): HistoricoDTO {

        val cliente = clienteRepository.findById(request.clienteId)
            .orElseThrow {
                NotFoundException("Cliente com id ${request.clienteId} não encontrado")
            }

        val filme = filmeRepository.findById(request.filmeId)
            .orElseThrow {
                NotFoundException("Filme com id ${request.filmeId} não encontrado")
            }

        if (filme.status == FilmeStatus.ALUGADO) {
            throw BadRequestException("O filme já está alugado")
        }

        val filmeAtualizado = filme.copy(
            status = FilmeStatus.ALUGADO
        )

        val historico = Historico(
            id = UUID.randomUUID(),
            cliente = cliente,
            filme = filmeAtualizado,
            dataAluguel = LocalDateTime.now(),
            dataDevolucao = null
        )

        filmeRepository.save(filmeAtualizado)
        val historicoSalvo = historicoRepository.save(historico)

        return HistoricoDTO.fromEntity(historicoSalvo)

    }

    override fun devolverFilme(historicoId: UUID): HistoricoDTO {

        val historico = historicoRepository.findById(historicoId)
            .orElseThrow {
                NotFoundException("Histórico com id $historicoId não encontrado")
            }

        if (historico.dataDevolucao != null) {
            throw BadRequestException("Este aluguel já foi devolvido")
        }

        val filme = historico.filme

        if (filme.status == FilmeStatus.DISPONIVEL) {
            throw BadRequestException("O filme já está disponível")
        }

        val filmeAtualizado = filme.copy(
            status = FilmeStatus.DISPONIVEL
        )

        val historicoAtualizado = historico.copy(
            dataDevolucao = LocalDateTime.now()
        )

        filmeRepository.save(filmeAtualizado)
        val historicoSalvo = historicoRepository.save(historicoAtualizado)

        return HistoricoDTO.fromEntity(historicoSalvo)
    }

    override fun buscarPorFilmeId(filmeId: UUID): List<HistoricoDTO> {
        filmeRepository.findById(filmeId)
            .orElseThrow {
                NotFoundException("Filme com id $filmeId não encontrado")
            }
        return historicoRepository.findByFilmeId(filmeId)
            .map { HistoricoDTO.fromEntity(it) }
    }

    override fun buscarPorClienteId(clienteId: UUID): List<HistoricoDTO> {
        clienteRepository.findById(clienteId)
            .orElseThrow {
                NotFoundException("Cliente com id $clienteId não encontrado")
            }

        return historicoRepository.findByClienteId(clienteId)
            .map { HistoricoDTO.fromEntity(it) }


    }
}
