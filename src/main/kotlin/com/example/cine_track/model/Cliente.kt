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
data class Cliente (
    @Id
    val id: UUID,
    @Column(name = "nome")
    val nome: String,
    @Column(name = "cpf")
    val cpf: String,
    @Column(name = "email")
    val email: String,
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    val status: ClienteStatus,
)