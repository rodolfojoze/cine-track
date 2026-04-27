package com.example.cine_track.controller.filme

import com.example.cine_track.model.Filme
import com.example.cine_track.model.FilmeStatus
import java.util.*

data class FilmeDTO (
    val id: UUID,
    val titulo: String,
    val genero: String,
    val anoLancamento: String,
    val status: FilmeStatus
) {
    companion object {
        fun fromEntity(filme: Filme): FilmeDTO {
            return FilmeDTO(
                id = filme.id,
                titulo = filme.titulo,
                genero = filme.genero,
                anoLancamento = filme.anoLancamento,
                status = filme.status
            )
        }
    }
}