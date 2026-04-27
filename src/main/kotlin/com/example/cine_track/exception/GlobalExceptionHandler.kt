package com.example.cine_track.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import com.example.cine_track.exception.ErrorResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class GlobalExceptionHandler {

    private fun buildErrorResponse(
        status: HttpStatus,
        message: String?,
        request: HttpServletRequest
    ) : ErrorResponse {
        return ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = status.value(),
            error = status.name,
            message = message,
            path = request.requestURI
        )
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(
        ex: NotFoundException,
        request: HttpServletRequest
    ) : ResponseEntity<ErrorResponse> {

        val errorResponse = buildErrorResponse(
            status = HttpStatus.NOT_FOUND,
            message = ex.message,
            request = request,
        )
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(errorResponse)

    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequest(
        ex: BadRequestException,
        request: HttpServletRequest
    ) : ResponseEntity<ErrorResponse> {
        val errorResponse = buildErrorResponse(
            status = HttpStatus.BAD_REQUEST,
            message = ex.message,
            request = request,
        )
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(errorResponse)
    }

    @ExceptionHandler(ConflictException::class)
    fun handleConflict(
        ex: ConflictException,
        request: HttpServletRequest
    ) : ResponseEntity<ErrorResponse> {
        val errorResponse = buildErrorResponse(
            status = HttpStatus.CONFLICT,
            message = ex.message,
            request = request,
        )
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(errorResponse)
    }
}
