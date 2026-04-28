package com.example.cine_track.controller.filme

import com.example.cine_track.service.filme.FilmeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import java.util.UUID

@RestController
@RequestMapping("/filmes")
class FilmeController(
    private val service: FilmeService
) {

    @GetMapping
    fun listarFilmes(): ResponseEntity<List<FilmeDTO>> {
        return ResponseEntity.ok(service.listarTodos())
    }

    @GetMapping("/{id}")
    fun filmePorID(@PathVariable id: UUID
    ): ResponseEntity<FilmeDTO> {

        val filme = service.buscarPorId(id)
        return ResponseEntity.ok(filme)
    }

     @PostMapping
     fun criarFilme(
         @RequestBody request: CriarFilmeRequest
     ): ResponseEntity<FilmeDTO> {

         val filmeCriado = service.criarFilme(request)

         return ResponseEntity
             .status(HttpStatus.CREATED)
             .body(filmeCriado)
     }

    @PutMapping("/{id}")
    fun editarFilme(
        @PathVariable id: UUID,
        @RequestBody request: EditarFilmeRequest
    ) : ResponseEntity<FilmeDTO> {

        val filmeEditado = service.editarFilme(id, request)

        return ResponseEntity.ok(filmeEditado)
    }

}