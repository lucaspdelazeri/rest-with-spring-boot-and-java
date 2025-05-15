package br.com.lpd.exception;

public record ExceptionResponse(
        String code,
        String status,
        String message
) {}
