package com.example.cine_track.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Table
@Entity
data class Filme(
    @Id
    val id: UUID,
    @Column (name = "titulo")
    val titulo: String,
    @Column (name = "genero")
    val genero: String,
    @Column (name = "anoLancamento")
    val anoLancamento: String,
    @Column (name = "status")
    @Enumerated(EnumType.STRING)
    val status: FilmeStatus,
)
