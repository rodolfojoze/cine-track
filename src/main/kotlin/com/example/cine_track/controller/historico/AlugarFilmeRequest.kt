package com.example.cine_track.controller.historico

import java.util.UUID

class AlugarFilmeRequest (
    val clienteId: UUID,
    val filmeId: UUID
)