package com.example.cine_track.controller.filme

data class CriarFilmeRequest (
    val titulo: String,
    val genero: String,
    val anoLancamento: String
)