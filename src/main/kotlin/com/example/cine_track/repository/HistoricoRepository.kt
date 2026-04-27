package com.example.cine_track.repository

import com.example.cine_track.model.Historico
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface HistoricoRepository: JpaRepository<Historico, UUID> {
    fun findByFilmeId(filmeId: UUID): List<Historico>
    fun findByClienteId(clienteId: UUID): List<Historico>

}