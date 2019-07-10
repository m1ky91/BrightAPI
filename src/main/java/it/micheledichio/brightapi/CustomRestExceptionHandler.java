package it.micheledichio.brightapi;

import it.micheledichio.brightapi.dto.Error;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<Error> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new Error(fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        if (fieldErrors.size() == 1) {
            return handleExceptionInternal(
                    ex, fieldErrors.stream().findFirst().get(), headers, HttpStatus.BAD_REQUEST, request);
        } else {
            return handleExceptionInternal(
                    ex, fieldErrors, headers, HttpStatus.BAD_REQUEST, request);
        }
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex, final WebRequest request) {
        return new ResponseEntity<>(new Error("InvalidArgument"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
