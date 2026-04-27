package com.example.cine_track.service.filme

import com.example.cine_track.controller.filme.CriarFilmeRequest
import com.example.cine_track.controller.filme.EditarFilmeRequest
import com.example.cine_track.controller.filme.FilmeDTO
import java.util.UUID

interface FilmeService {
    fun listarTodos(): List<FilmeDTO>
    fun buscarPorId(id: UUID): FilmeDTO
    fun criarFilme(request: CriarFilmeRequest): FilmeDTO
    fun editarFilme(
        id: UUID,
        request: EditarFilmeRequest
    ): FilmeDTO
}