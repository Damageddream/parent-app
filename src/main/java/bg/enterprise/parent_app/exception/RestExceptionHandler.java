package bg.enterprise.parent_app.exception;

import bg.enterprise.parent_app.exception.exc.*;
import bg.enterprise.parent_app.util.ExceptionHandlingUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

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
}
