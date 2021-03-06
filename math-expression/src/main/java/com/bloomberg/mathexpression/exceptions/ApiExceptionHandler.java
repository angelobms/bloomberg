package com.bloomberg.mathexpression.exceptions;

import com.bloomberg.mathexpression.web.errors.ErrorMessage;
import com.bloomberg.mathexpression.web.errors.ErrorMessages;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Locale;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RestController
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String ERROR_MESSAGE_SUFIX = ".message";
    private static final String ERROR_DETAIL_SUFIX = ".detail";
    private static final String ERROR_TYPE_FORMAT = "errors/%s";
    private static final String ERROR_HELP_FORMAT = "%s/%s";
    private static final String ERROR_MESSAGE_PREFIX = "error.default.";
    private static final String STANDARD_ERROR_URI_FORMAT = "standards/%s";

    private final MessageSource messageSource;

    @Autowired
    public ApiExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        ErrorMessage[] errors = result.getAllErrors()
                .stream()
                .map(objectError -> buildMessageError(objectError.getDefaultMessage(),
                        status, request, objectError.getArguments()))
                .toArray(ErrorMessage[]::new);
        return buildResponseEntity(status, request, errors);
    }

    @Override
    public ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(ErrorCode.MISSING_REQUEST_PARAMETER,
                HttpStatus.BAD_REQUEST, request, ex.getParameterName());
    }

    @Override
    public ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
            HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(ErrorCode.MEDIA_TYPE_NOT_ACCEPTABLE,
                HttpStatus.UNSUPPORTED_MEDIA_TYPE, request, ex.getSupportedMediaTypes());
    }

    @Override
    public ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(ErrorCode.MEDIA_TYPE_NOT_SUPPORTABLE,
                HttpStatus.UNSUPPORTED_MEDIA_TYPE, request, ex.getSupportedMediaTypes());
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(ErrorCode.JSON_REQUEST_MALFORMED,
                HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(FeignException.BadRequest.class)
    public ResponseEntity<?> handleFeignStatusException(FeignException ex, HttpServletResponse response) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        if (ex instanceof FeignBadResponseWrapper) {
            return buildResponseEntity(status, null, ((FeignBadResponseWrapper) ex).getErrors());
        } else {
            return buildResponseEntity(status, null);
        }
    }
    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<?> handleServiceUnavailable(ServiceUnavailableException ex, WebRequest request) {
        return buildResponseEntity(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildDefaultResponseEntity(request);
    }

    private ResponseEntity<Object> buildResponseEntity(String keyMessage,
                                                       HttpStatus status, WebRequest request, Object... args) {

        return buildResponseEntity(status, request, buildMessageError(keyMessage, status, request, args));
    }

    private ResponseEntity<Object> buildResponseEntity(HttpStatus status,
                                                       WebRequest request, ErrorMessage... errors) {
        ErrorMessages messageErrors = new ErrorMessages();
        messageErrors.getErrors().addAll(Arrays.asList(errors));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
        return new ResponseEntity<>(messageErrors, headers, status);
    }

    private ResponseEntity<Object> buildDefaultResponseEntity(WebRequest request) {

        String keyMessage = ErrorCode.INTERNAL_SERVER_ERROR;

        return buildResponseEntity(keyMessage, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ErrorMessage buildMessageError(String keyMessage,
                                           HttpStatus status, WebRequest request, Object... args) {
        ErrorMessage error = new ErrorMessage();
        String errorCode = keyMessage
                .replace(ERROR_MESSAGE_PREFIX, "")
                .replace(ERROR_MESSAGE_SUFIX, "");
        error.setType(String.format(ERROR_TYPE_FORMAT, errorCode));
        error.setTitle(getMessage(keyMessage, request.getLocale(), args));
        error.setDetail(getDetailMessage(keyMessage, request.getLocale(), args));
        error.setStatus(String.valueOf(status.value()));
        error.setHelp(String.format(ERROR_HELP_FORMAT, getMessage(ErrorCode.ERROR_PAGE, request.getLocale()),
                (keyMessage.startsWith(ERROR_MESSAGE_PREFIX)) ?
                        String.format(STANDARD_ERROR_URI_FORMAT, errorCode) : errorCode));
        error.setInstance(getDecodeUrl(request));
        return error;
    }

    private String getDecodeUrl(WebRequest request) {
        String instance;
        try {
            instance = ((ServletWebRequest) request).getRequest().getRequestURI();
        } catch (Exception e) {
            instance = null;
        }
        return instance;
    }

    private String getMessage(String keyMessage, Locale locale, Object... args) {
        String message;
        try {
            message = messageSource.getMessage(keyMessage, args, locale);
        } catch (Exception e) {
            message = keyMessage;
        }
        return message;
    }

    private String getDetailMessage(String keyMessage, Locale locale, Object... args) {

        keyMessage = (keyMessage.endsWith(ERROR_MESSAGE_SUFIX)) ?
                keyMessage.replace(ERROR_MESSAGE_SUFIX, ERROR_DETAIL_SUFIX) : null;

        String message = getMessage(keyMessage, locale, args);

        if (message.startsWith("error.")) {
            message = null;
        }
        return message;
    }
}
