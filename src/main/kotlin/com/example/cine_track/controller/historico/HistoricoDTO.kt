package com.example.cine_track.controller.historico

import com.example.cine_track.model.Historico
import java.util.UUID

class HistoricoDTO (
    val id: UUID,
    val filmeId: UUID,
    val filmeTitulo: String,
    val clienteId: UUID,
    val clienteNome: String,
    val dataAluguel: String,
    val dataDevolucao: String?
    ) {

    companion object {
        fun fromEntity (historico: Historico): HistoricoDTO {
            return HistoricoDTO(
                id = historico.id,
                filmeId = historico.filme.id,
                filmeTitulo = historico.filme.titulo,
                clienteId = historico.cliente.id,
                clienteNome = historico.cliente.nome,
                dataAluguel = historico.dataAluguel.toString(),
                dataDevolucao = historico.dataDevolucao?.toString()
            )
        }
    }
}