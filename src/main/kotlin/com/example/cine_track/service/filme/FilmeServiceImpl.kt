package com.example.cine_track.service.filme

import com.example.cine_track.controller.filme.CriarFilmeRequest
import com.example.cine_track.controller.filme.EditarFilmeRequest
import com.example.cine_track.controller.filme.FilmeDTO
import com.example.cine_track.exception.ConflictException
import com.example.cine_track.exception.NotFoundException
import com.example.cine_track.model.Filme
import com.example.cine_track.model.FilmeStatus
import com.example.cine_track.repository.FilmeRepository
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class FilmeServiceImpl(
    private val filmeRepository: FilmeRepository,
) : FilmeService {

    override fun listarTodos(): List<FilmeDTO> {
        val filmesEncontrados = filmeRepository.findAll()
        return filmesEncontrados.map { FilmeDTO.fromEntity(it) }

    }

    override fun buscarPorId(id: UUID): FilmeDTO {
        val filme = filmeRepository.findById(id)
            .orElseThrow {
                NotFoundException("Filme com id $id não encontrado")
            }
        return FilmeDTO.fromEntity(filme)
    }

    override fun criarFilme(request: CriarFilmeRequest): FilmeDTO {
        val existeFilme = filmeRepository.existsByTituloAndAnoLancamento(
            request.titulo, request.anoLancamento
        )
        if (existeFilme) {
            throw ConflictException(
                "Já existe um filme cadastro com esse titulo e ano"
            )
        }

        val filme = Filme(
            id = UUID.randomUUID(),
            titulo = request.titulo,
            genero = request.genero,
            anoLancamento = request.anoLancamento,
            status = FilmeStatus.DISPONIVEL
        )
        val filmeSalvo = filmeRepository.save(filme)
        return FilmeDTO.fromEntity(filmeSalvo)
    }

    override fun editarFilme(
        id: UUID,
        request: EditarFilmeRequest
    ): FilmeDTO {

        val filme = filmeRepository.findById(id)
            .orElseThrow {
                NotFoundException("Filme com o id: $id não encontrado")
            }

        val filmeAtualizado = filme.copy(
            titulo = request.titulo ?: filme.titulo,
            genero = request.genero ?: filme.genero,
            anoLancamento = request.anoLancamento ?: filme.anoLancamento
        )

        val filmeSalvo = filmeRepository.save(filmeAtualizado)
        return FilmeDTO.fromEntity(filmeSalvo)

    }
}
