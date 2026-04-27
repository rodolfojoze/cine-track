package com.example.cine_track.exception

import java.time.LocalDateTime

class ErrorResponse (
    val timestamp: LocalDateTime,
    val status: Int,
    val error: String,
    val message: String?,
    val path: String
){
}