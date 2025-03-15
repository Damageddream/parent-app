package bg.enterprise.parent_app.exception;

import bg.enterprise.parent_app.exception.exc.*;
import bg.enterprise.parent_app.util.ExceptionHandlingUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(EntityNotFoundException exc) {

        ErrorResponse error = new ErrorResponse(ExceptionHandlingUtils.getRootCauseMessage(exc), Instant.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityAlreadyExists.class)
    public  ResponseEntity<ErrorResponse> handleException(EntityAlreadyExists exc) {
        ErrorResponse error = new ErrorResponse(ExceptionHandlingUtils.getRootCauseMessage(exc), Instant.now());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    // added for dev, to see full error stack in postman
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception exc) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", exc.getMessage());
        errorDetails.put("timestamp", Instant.now());
        errorDetails.put("stackTrace", Arrays.stream(exc.getStackTrace())
                .map(StackTraceElement::toString)
                .collect(Collectors.toList()));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
