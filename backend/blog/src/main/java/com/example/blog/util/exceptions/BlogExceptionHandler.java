package com.example.blog.backend.util.exceptions;

import javassist.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.blog.backend.generated.model.Error;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;


@ControllerAdvice
public class BlogExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> noSuchUserException(EntityNotFoundException e) {
        Error error = new Error();
        error.setCode("404");
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NumberFormatException.class})
    public ResponseEntity<Object> numberFormatException() {
        Error error = new Error();
        error.setCode("400");
        error.setMessage("Неверный формат ID");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<Object> baseError(AccessDeniedException e) {
        Error error = new Error();
        error.setCode("403");
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> baseError(MethodArgumentNotValidException e) {
        Error error = new Error();
        error.setCode("400");
        String message = e.getMessage();
        if (message.contains("rejected value [null]")) {
            error.setMessage("Поля не могут быть пустыми и содержать пробелы");
        } else {
            if (message.contains("'password': rejected value")) {
                error.setMessage("Неверный формат пароля. Пароль должен быть минимим 8 символов, содержать заглавную букву и цифру");
            } else if (message.contains("'email': rejected value")) {
                error.setMessage("Неверный формат email");
            } else if (message.contains("'name': rejected value")) {
                error.setMessage("Неверный формат имени. Имя должно быть минимум 4 символа");
            }
        }


        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<Object> noSuchUserException(ValidationException e) {
        Error error = new Error();
        error.setCode("400");
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}