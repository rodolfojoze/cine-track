package com.example.cine_track.repository

import com.example.cine_track.model.Cliente
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ClienteRepository : JpaRepository<Cliente, UUID>{
    fun existsByCpf(
        cpf: String
    ) : Boolean
}