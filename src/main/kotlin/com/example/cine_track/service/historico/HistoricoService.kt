package com.example.cine_track.service.historico

import com.example.cine_track.controller.historico.AlugarFilmeRequest
import com.example.cine_track.controller.historico.HistoricoDTO
import java.util.UUID

interface HistoricoService {
    fun alugarFilme(request: AlugarFilmeRequest): HistoricoDTO
    fun devolverFilme(historicoId: UUID): HistoricoDTO
    fun buscarPorFilmeId(filmeId: UUID): List<HistoricoDTO>
    fun buscarPorClienteId(clienteId: UUID): List<HistoricoDTO>
}