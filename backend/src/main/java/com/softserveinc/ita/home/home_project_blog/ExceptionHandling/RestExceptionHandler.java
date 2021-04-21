package com.softserveinc.ita.home.home_project_blog.ExceptionHandling;

import org.hibernate.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

//@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Error> noSuchUserException(EntityNotFoundException e){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        Error error = new Error(
                httpStatus.toString(),
                e.getMessage()
        );
        return new ResponseEntity<>(error,httpStatus);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Error> constraintViolationException(ConstraintViolationException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Error error = new Error(
                httpStatus.toString(),
                e.getMessage()
        );
        return new ResponseEntity<>(error,httpStatus);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
//        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
//                .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                .findFirst()
//                .orElse(ex.getMessage());
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Error error = new Error(
                httpStatus.toString(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, httpStatus.BAD_REQUEST);
    }
//    @Override
//    public RestponseEntity<Error> ResponseEntityExceptionHandler.handleMethodArgumentNotValid(){
//        return new ResponseEntity<>(new Error, HttpStatus.BAD_REQUEST);
//    }

    //can't build with this exception :(
//    override ResponseEntityExceptionHandler.handleMethodArgumentNotValid

//    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
//    public ResponseEntity<Error> methodArgumentNotValidException(MethodArgumentNotValidException e){
//        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
//        Error error = new Error(
//                httpStatus.toString(),
//                e.getMessage()
//        );
//        return new ResponseEntity<>(error,httpStatus);
//    }

    //doesn't work :(
    @ExceptionHandler
    public ResponseEntity<Error> anyException(Exception e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Error error = new Error(
                httpStatus.toString(),
                e.getMessage()
        );
        return new ResponseEntity<>(error,httpStatus);
    }


    @ExceptionHandler(value = {NumberFormatException.class})
    public ResponseEntity<Error> validationException(NumberFormatException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Error error = new Error(
                httpStatus.toString(),
                e.getMessage()
        );
        return new ResponseEntity<>(error,httpStatus);
    }

//    @Override
//    protected ResponseEntity<Object> handleHttpMessageNotReadable(
//            HttpMessageNotReadableException ex,
//            HttpHeaders headers, HttpStatus status, WebRequest request) {
//        String error = "Malformed JSON request";
//        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
//    }
//
//    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
//        return new ResponseEntity<>(apiError, apiError.getStatus());
//    }
}