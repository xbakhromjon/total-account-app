package uz.xbakhromjon.common.exception;

import jakarta.servlet.ServletException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.HashMap;

@RestControllerAdvice
@Slf4j
public class ExceptionTranslator {

    @ExceptionHandler(value = {UserErrorResponseException.class})
    public ResponseEntity<ProblemDetail> handle(UserErrorResponseException ex, ServletWebRequest request) {
        StackTraceElement stackTraceElement = ex.getStackTrace()[0];
        String[] split = stackTraceElement.getClassName().split("\\.");
        String className = split[split.length - 1];
        return new ResponseEntity<>(
                ProblemDetail.builder()
                        .status(ex.getStatus().value())
                        .type(ex.getClass().getSimpleName())
                        .details(ex.getMessage())
                        .properties(new HashMap<>())
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequest().getRequestURI())
                        .build(), ex.getStatus());
    }

    @ExceptionHandler(value = {ClientErrorResponseException.class})
    public ResponseEntity<ProblemDetail> handle(ClientErrorResponseException ex, ServletWebRequest request) {
        StackTraceElement stackTraceElement = ex.getStackTrace()[0];
        String[] split = stackTraceElement.getClassName().split("\\.");
        String className = split[split.length - 1];
        return new ResponseEntity<>(
                ProblemDetail.builder()
                        .status(ex.getStatus().value())
                        .type(ex.getClass().getSimpleName())
                        .details(ex.getMessage())
                        .properties(new HashMap<>())
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequest().getRequestURI())
                        .build(), ex.getStatus());
    }


    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ProblemDetail> handle(MethodArgumentNotValidException ex, ServletWebRequest request) {
        HashMap<String, Object> properties = new HashMap<>();
        String fieldErrorMessageTemplate = "%s; rejected value: %s";
        for (FieldError fieldError : ex.getFieldErrors()) {
            properties.put(fieldError.getField(), fieldErrorMessageTemplate.formatted(fieldError.getDefaultMessage(), fieldError.getRejectedValue()));
        }
        ProblemDetail problemDetail = ProblemDetail.builder()
                .status(ex.getStatusCode().value())
                .type(ex.getClass().getSimpleName())
                .details(ex.getMessage())
                .properties(properties)
                .timestamp(LocalDateTime.now())
                .path(request.getRequest().getRequestURI())
                .build();

        return new ResponseEntity<>(
                problemDetail, ex.getStatusCode());
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<ProblemDetail> handle(ValidationException ex, ServletWebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .type(ex.getClass().getSimpleName())
                .details(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .path(request.getRequest().getRequestURI())
                .build();

        return new ResponseEntity<>(
                problemDetail, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ProblemDetail> handle(RuntimeException ex, ServletWebRequest request) {
        StackTraceElement stackTraceElement = ex.getStackTrace()[0];
        String[] split = stackTraceElement.getClassName().split("\\.");
        String className = split[split.length - 1];
        return new ResponseEntity<>(
                ProblemDetail.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .type(ex.getClass().getSimpleName())
                        .details(ex.getMessage())
                        .properties(new HashMap<>())
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequest().getRequestURI())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = {ServletException.class})
    public ResponseEntity<ProblemDetail> handle(ServletException ex, ServletWebRequest request) {
        return new ResponseEntity<>(
                ProblemDetail.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .type(ex.getClass().getSimpleName())
                        .details(ex.getMessage())
                        .properties(new HashMap<>())
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequest().getRequestURI())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<ProblemDetail> handle(HttpMessageNotReadableException ex, ServletWebRequest request) {
        return new ResponseEntity<>(
                ProblemDetail.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .type(ex.getClass().getSimpleName())
                        .details(ex.getMessage())
                        .properties(new HashMap<>())
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequest().getRequestURI())
                        .build(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<ProblemDetail> handle(AccessDeniedException ex, ServletWebRequest request) {
        return new ResponseEntity<>(
                ProblemDetail.builder()
                        .status(HttpStatus.FORBIDDEN.value())
                        .type(ex.getClass().getSimpleName())
                        .details(ex.getMessage())
                        .properties(new HashMap<>())
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequest().getRequestURI())
                        .build(), HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ProblemDetail> handle(HttpRequestMethodNotSupportedException ex, ServletWebRequest request) {
        return new ResponseEntity<>(
                ProblemDetail.builder()
                        .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                        .type(ex.getClass().getSimpleName())
                        .details(ex.getMessage())
                        .properties(new HashMap<>())
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequest().getRequestURI())
                        .build(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public ResponseEntity<ProblemDetail> handle(MissingServletRequestParameterException ex, ServletWebRequest request) {
        return new ResponseEntity<>(
                ProblemDetail.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .type(ex.getClass().getSimpleName())
                        .details(ex.getMessage())
                        .properties(new HashMap<>())
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequest().getRequestURI())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<ProblemDetail> handle(ConstraintViolationException ex, ServletWebRequest request) {
        HashMap<String, Object> properties = new HashMap<>();
        String fieldErrorMessageTemplate = "%s; rejected value: %s";
        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            properties.put(violation.getPropertyPath().toString(), fieldErrorMessageTemplate.formatted(violation.getMessage(), violation.getInvalidValue()));
        }
        StringBuilder displayMessage = new StringBuilder();
        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            displayMessage.append(violation.getPropertyPath() + " " + violation.getMessage()).append("; ");
        }
        ProblemDetail problemDetail = ProblemDetail.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .type(ex.getClass().getSimpleName())
                .details(ex.getMessage())
                .properties(properties)
                .timestamp(LocalDateTime.now())
                .path(request.getRequest().getRequestURI())
                .build();

        return new ResponseEntity<>(
                problemDetail, HttpStatus.BAD_REQUEST);
    }
}
