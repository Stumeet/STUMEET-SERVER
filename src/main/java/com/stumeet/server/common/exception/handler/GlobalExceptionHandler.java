package com.stumeet.server.common.exception.handler;

import java.util.List;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.stumeet.server.common.exception.model.BadRequestException;
import com.stumeet.server.common.exception.model.BusinessException;
import com.stumeet.server.common.exception.model.SecurityViolationException;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.common.model.ApiResponse;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_LOG_MESSAGE = "[ERROR] {} : {}";
    private static final String WARN_LOG_MESSAGE = "[WARN] {} : {}";
    private static final String SECURITY_LOG_MESSAGE = "[SECURITY] {} : {}";

    @ExceptionHandler(SecurityViolationException.class)
    protected ResponseEntity<ApiResponse> handleSecurityViolationException(final SecurityViolationException e) {
        log.warn(SECURITY_LOG_MESSAGE, e.getClass().getSimpleName(), e.getMessage());
        log.debug(e.getMessage(), e);
        e.printStackTrace();

        ErrorCode errorCode = e.getErrorCode();
        ApiResponse response = ApiResponse.fail(errorCode);

        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ApiResponse> handleBusinessException(final BusinessException e) {
        log.warn(WARN_LOG_MESSAGE, e.getClass().getSimpleName(), e.getMessage());
        log.debug(e.getMessage(), e);
        e.printStackTrace();

        ErrorCode errorCode = e.getErrorCode();
        ApiResponse response = ApiResponse.fail(errorCode);

        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(CompletionException.class)
    protected ResponseEntity<ApiResponse> handleCompletionException(final CompletionException e) {
        String message = "비동기 작업 중 에러 발생. 원인: " + e.getCause().getMessage();

        log.error(ERROR_LOG_MESSAGE, e.getClass().getSimpleName(), e.getMessage());
        log.error(message);
        log.debug(e.getMessage(), e);
        e.printStackTrace();

        ApiResponse response = ApiResponse.fail(500, message);

        return ResponseEntity.internalServerError()
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiResponse> handleException(final Exception e) {
        log.error(ERROR_LOG_MESSAGE, e.getClass().getSimpleName(), e.getMessage());
        log.debug(e.getMessage(), e);
        e.printStackTrace();

        ApiResponse response = ApiResponse.fail(ErrorCode.INTERNAL_SERVER_ERROR);

        return ResponseEntity.internalServerError()
                .body(response);
    }

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ApiResponse> handleBindException(final BindException e) {
        log.warn(ERROR_LOG_MESSAGE, e.getClass().getSimpleName(), e.getMessage());

        ApiResponse response = ApiResponse.fail(ErrorCode.BIND_EXCEPTION, e);

        return ResponseEntity.badRequest()
                .body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ApiResponse> handleConstraintViolationException(final ConstraintViolationException e) {
        log.warn(ERROR_LOG_MESSAGE, e.getClass().getSimpleName(), e.getMessage());

        List<String> errors = e.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());

        ApiResponse response = ApiResponse.fail(HttpStatus.BAD_REQUEST.value(), String.join(", ", errors));

        return ResponseEntity.badRequest()
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException e) {
        log.warn(ERROR_LOG_MESSAGE, e.getClass().getSimpleName(), e.getMessage());

        ApiResponse response = ApiResponse.fail(ErrorCode.METHOD_ARGUMENT_NOT_VALID_EXCEPTION, e);

        return ResponseEntity.badRequest()
                .body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ApiResponse> handleMethodArgumentTypeMismatchException(
            final MethodArgumentTypeMismatchException e) {
        log.warn(ERROR_LOG_MESSAGE, e.getClass().getSimpleName(), e.getMessage());

        ApiResponse response = ApiResponse.fail(ErrorCode.METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION);

        return ResponseEntity.badRequest()
                .body(response);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    protected ResponseEntity<ApiResponse> handleMaxUploadSizeExceededException(final MaxUploadSizeExceededException e) {
        log.warn(ERROR_LOG_MESSAGE, e.getClass().getSimpleName(), e.getMessage());

        ApiResponse response = ApiResponse.fail(ErrorCode.FILE_SIZE_LIMIT_EXCEEDED_EXCEPTION);

        return ResponseEntity.badRequest()
                .body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ApiResponse> handleCustomBadRequestException(final BadRequestException e) {
        log.warn(ERROR_LOG_MESSAGE, e.getClass().getSimpleName(), e.getMessage());
        e.printStackTrace();

        String message = String.format("%s %s", e.getErrorCode().getMessage(), e.getMessage());
        ApiResponse response = ApiResponse.fail(e.getErrorCode().getHttpStatusCode(), message);

        return new ResponseEntity<>(response, e.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            InvalidFormatException.class,
            ServletRequestBindingException.class,
            MissingServletRequestParameterException.class,
            HandlerMethodValidationException.class
    })
    protected ResponseEntity<ApiResponse> handleInvalidFormatException(final Exception e) {
        log.warn(ERROR_LOG_MESSAGE, e.getClass().getSimpleName(), e.getMessage());

        ApiResponse response = ApiResponse.fail(ErrorCode.INVALID_FORMAT_EXCEPTION);

        return ResponseEntity.badRequest()
                .body(response);
    }
}

