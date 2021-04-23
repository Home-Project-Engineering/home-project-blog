package com.softserveinc.ita.home.home_project_blog.Validation;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.hibernate.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

//@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Error> noSuchUserException(EntityNotFoundException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        Error error = new Error(
                httpStatus.toString(),
                e.getMessage()
        );
        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler(value = {InvalidFormatException.class})
    public ResponseEntity<Error> noSuchRoleException(InvalidFormatException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        Error error = new Error(
                httpStatus.toString(),
                e.getMessage()
        );
        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onConstraintValidationException(
            ConstraintViolationException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.getViolations().add(
                    new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return error;
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    ValidationErrorResponse onMethodArgumentNotValidException(
//            MethodArgumentNotValidException e) {
//        ValidationErrorResponse error = new ValidationErrorResponse();
//        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
//            error.getViolations().add(
//                    new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
//        }
//        return error;
//    }


    //@ExceptionHandler(value = {ConstraintViolationException.class})
//    public ResponseEntity<Error> constraintViolationException(ConstraintViolationException e){
//        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
//        Error error = new Error(
//                httpStatus.toString(),
//                e.getMessage()
//        );
//        return new ResponseEntity<>(error,httpStatus);
//    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.getViolations().add(
                    new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
//        Error error = new Error(
//                httpStatus.toString(),
//                ex.getMessage()
//        );
        return new ResponseEntity<>(error, httpStatus);
    }


//    @Override
//    public ResponseEntity<Error> ResponseEntityExceptionHandler.handleMethodArgumentNotValid(){
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
    public ResponseEntity<Error> anyException(Exception e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Error error = new Error(
                httpStatus.toString(),
                e.getMessage()
        );
        return new ResponseEntity<>(error, httpStatus);
    }


    @ExceptionHandler(value = {NumberFormatException.class})
    public ResponseEntity<Error> validationException(NumberFormatException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Error error = new Error(
                httpStatus.toString(),
                e.getMessage()
        );
        return new ResponseEntity<>(error, httpStatus);
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