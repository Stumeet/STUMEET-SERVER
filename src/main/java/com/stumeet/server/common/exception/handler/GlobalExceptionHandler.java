package com.stumeet.server.common.exception.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.stumeet.server.common.exception.model.BadRequestException;
import com.stumeet.server.common.exception.model.BusinessException;
import com.stumeet.server.common.exception.model.InvalidStateException;
import com.stumeet.server.common.exception.model.NotExistsException;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.common.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;

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

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ApiResponse> handleBusinessException(final BusinessException e) {
        log.warn(WARN_LOG_MESSAGE, e.getClass().getSimpleName(), e.getMessage());
        log.debug(e.getMessage(), e);

        ErrorCode errorCode = e.getErrorCode();
        ApiResponse response = ApiResponse.fail(errorCode);

        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiResponse> handleException(final Exception e) {
        log.error(ERROR_LOG_MESSAGE, e.getClass().getSimpleName(), e.getMessage());
        log.debug(e.getMessage(), e);

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        log.warn(ERROR_LOG_MESSAGE, e.getClass().getSimpleName(), e.getMessage());

        ApiResponse response = ApiResponse.fail(ErrorCode.METHOD_ARGUMENT_NOT_VALID_EXCEPTION, e);

        return ResponseEntity.badRequest()
                .body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ApiResponse> handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException e) {
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

    @ExceptionHandler({
        BadRequestException.class,
        // NotExistsException.class,
        // InvalidStateException.class
    })
    protected ResponseEntity<ApiResponse> handleCustomBadRequestException(final BusinessException e) {
        log.warn(ERROR_LOG_MESSAGE, e.getClass().getSimpleName(), e.getMessage());

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

