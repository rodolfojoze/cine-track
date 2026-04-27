package com.example.cine_track.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@Table
@Entity
data class Historico(
    @Id
    val id: UUID,
    @ManyToOne
    @JoinColumn(name = "filme_id")
    val filme: Filme,
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    val cliente: Cliente,
    @Column(name = "data_aluguel")
    val dataAluguel: LocalDateTime,
    @Column(name = "data_devolucao")
    val dataDevolucao: LocalDateTime?
)

