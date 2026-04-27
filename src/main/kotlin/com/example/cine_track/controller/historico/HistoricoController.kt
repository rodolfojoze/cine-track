package com.example.cine_track.controller.historico

import com.example.cine_track.service.historico.HistoricoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/alugueis")
class HistoricoController(
    private val service: HistoricoService
) {
    @PostMapping
    fun alugarFilme(
        @RequestBody request: AlugarFilmeRequest
    ): ResponseEntity<HistoricoDTO> {

        val historico = service.alugarFilme(request)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(historico)
    }

    @PatchMapping("/{id}/devolver")
    fun devolverFilme (
        @PathVariable id: UUID
    ): ResponseEntity <HistoricoDTO> {

        val historico = service.devolverFilme(id)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(historico)
    }

    @GetMapping("/filme/{filmeId}")
    fun buscarPorFilme(
        @PathVariable filmeId: UUID
    ): List<HistoricoDTO> =
        service.buscarPorFilmeId(filmeId)

    @GetMapping("/cliente/{clienteId}")
    fun buscarPorCliente(
        @PathVariable clienteId: UUID
    ): List<HistoricoDTO> =
        service.buscarPorClienteId(clienteId)

}