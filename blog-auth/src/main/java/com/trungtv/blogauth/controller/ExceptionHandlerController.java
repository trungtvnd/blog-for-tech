package com.trungtv.blogauth.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.trungtv.blogauth.constant.ConstantErrorCode;
import com.trungtv.blogauth.controller.dto.ValidationError;
import com.trungtv.blogauth.controller.response.BaseResponse;
import com.trungtv.blogauth.exception.CustomBusinessException;
import com.trungtv.blogauth.util.ExceptionUtil;
import com.trungtv.blogauth.util.MessageUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.LockAcquisitionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {
    @Data
    public static class ExceptionHandlingResponse {
        private int code;
        private Object data;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        private LocalDateTime timestamp;
        private String message;
        private String debugMessage;

        private ExceptionHandlingResponse() {
            timestamp = LocalDateTime.now();
        }

        ExceptionHandlingResponse(int code) {
            this();
            this.code = code;
        }

        ExceptionHandlingResponse(int code, Throwable ex) {
            this();
            this.code = code;
            this.message = "Unexpected error";
            this.debugMessage = ex.toString();
        }

        public ExceptionHandlingResponse(int code, Object data, String message) {
            this();
            this.code = code;
            this.data = data;
            this.message = message;
        }

        ExceptionHandlingResponse(int code, String message, Throwable ex) {
            this();
            this.code = code;
            this.message = message;
            this.debugMessage = ex.toString();
        }
    }

    private ResponseEntity<Object> buildResponseEntity(ExceptionHandlingResponse exceptionHandlingResponse) {
        BaseResponse baseResponse = BaseResponse.builder()
                .code(exceptionHandlingResponse.getCode())
                .message(exceptionHandlingResponse.getMessage())
                .build();
        return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(SecurityException.class)
    @ResponseBody
    public ResponseEntity<?> handlerExceptionUnAuthorization(SecurityException e) {
        log.error(e.getMessage(), e);
        return buildResponseEntity(new ExceptionHandlingResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), e));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<?> handlerBadRequestIllegalException(IllegalArgumentException e) {
        return buildResponseEntity(new ExceptionHandlingResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalStateException.class, TimeoutException.class, LockAcquisitionException.class, SocketTimeoutException.class, InterruptedException.class})
    @ResponseBody
    protected ResponseEntity<?> handleTimeOutException(Exception ex) {
        ex.printStackTrace();
        return buildResponseEntity(new ExceptionHandlingResponse(HttpStatus.BAD_REQUEST.value(), "ERROR", ex));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SQLException.class)
    @ResponseBody
    protected ResponseEntity<?> handleSQLException(SQLException ex) {
        ex.printStackTrace();
        return buildResponseEntity(new ExceptionHandlingResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), ex));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    protected ResponseEntity<?> handleException(Exception ex) {
        ex.printStackTrace();
        return buildResponseEntity(new ExceptionHandlingResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), ex));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseBody
    protected ResponseEntity<?> handleBadRequestIllegalArgumentException(NoSuchElementException ex) {
        return buildResponseEntity(new ExceptionHandlingResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), ex));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    protected ResponseEntity<?> handleBadRequestEntityNotFoundException(EntityNotFoundException ex) {
        return buildResponseEntity(new ExceptionHandlingResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), ex));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomBusinessException.class)
    @ResponseBody
    protected ResponseEntity<?> handleBadRequestEntityNotFoundException(CustomBusinessException ex) {
        BaseResponse baseResponse = BaseResponse.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ResponseEntity<?> onConstraintValidationException(ConstraintViolationException e) {
        ValidationError error = new ValidationError();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            error.getValidateDetails().put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        BaseResponse baseResponse = BaseResponse.builder()
                .code(ExceptionUtil.getCodeError(ConstantErrorCode.ValidateErrorCode.VALIDATION_ERROR))
                .message(ExceptionUtil.getMessageError(ConstantErrorCode.ValidateErrorCode.VALIDATION_ERROR))
                .data(error)
                .build();
        return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ResponseEntity<?> onConstraintValidationException(MethodArgumentNotValidException e) {

        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        ValidationError error = new ValidationError();
        for (FieldError violation : result.getFieldErrors()) {
            error.getValidateDetails().put(violation.getField(), violation.getDefaultMessage());
        }

        BaseResponse baseResponse = BaseResponse.builder()
                .code(ExceptionUtil.getCodeError(ConstantErrorCode.ValidateErrorCode.VALIDATION_ERROR))
                .message(ExceptionUtil.getMessageError(ConstantErrorCode.ValidateErrorCode.VALIDATION_ERROR))
                .data(error)
                .build();
        return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    protected ResponseEntity<?> onBadCredentialsException(BadCredentialsException e) {


        BaseResponse baseResponse = BaseResponse.builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .message(MessageUtils.getMessage(e.getMessage()))
                .build();
        return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
    }
}
