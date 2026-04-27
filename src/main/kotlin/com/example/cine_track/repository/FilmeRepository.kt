package com.example.cine_track.repository

import com.example.cine_track.model.Filme
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface FilmeRepository : JpaRepository<Filme, UUID>{
    fun existsByTituloAndAnoLancamento(
        titulo: String,
        anoLancamento: String
    ): Boolean
}
