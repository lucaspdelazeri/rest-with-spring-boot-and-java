package br.com.lpd.exception.handler;

import br.com.lpd.exception.ExceptionResponse;
import br.com.lpd.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class CustomEntityResponseHandler
        extends
            ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequests(Exception exception, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                exception.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception exception, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                String.valueOf(HttpStatus.NOT_FOUND.value()),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                exception.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        String message = "Invalid payload";
        Throwable cause = ex.getCause();

        if (cause instanceof InvalidFormatException ife)
        {
            String field = ife.getPath().stream()
                    .map(ref -> ref.getFieldName())
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse("unknown");

            Class<?> targetType = ife.getTargetType();

            message = buildMessage(field, targetType);
        }
        else if( cause instanceof ValueInstantiationException vie )
        {
            String field = vie.getPath().stream()
                    .map(ref -> ref.getFieldName())
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse("unknown");

            Class<?> targetType = vie.getType().getRawClass();

            message = buildMessage(field, targetType);
        }

        ExceptionResponse response = new ExceptionResponse(
                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                message
        );

        return ResponseEntity.badRequest().body(response);
    }

    private String buildMessage(String field, Class<?> targetType) {
        String message;
        if (targetType.isEnum()) {

            Object[] values = targetType.getEnumConstants();

            String allowed = Arrays.stream(values)
                    .map(v -> ((Enum<?>) v).name().toLowerCase())
                    .collect(Collectors.joining(", "));

            message = "Field '" + field
                    + "' invalid. Accepted values: " + allowed;
        }

        else if (LocalDate.class.isAssignableFrom(targetType)) {

            message = "Field '" + field
                    + "' an invalid format. Expected pattern dd/MM/yyyy";
        }

        else {
            message = "Field '" + field
                    + "' invalid. Expected: "
                    + targetType.getSimpleName();
        }
        return message;
    }
}
