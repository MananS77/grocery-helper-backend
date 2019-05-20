package com.manan.groceryhelper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by mananshah on 20/05/19.
 */

@ControllerAdvice
public class ItemExceptionController {
    @ExceptionHandler(value = ItemNotFoundException.class)
    public ResponseEntity<Object> exception (ItemNotFoundException exception) {
        return new ResponseEntity<Object>("Grocery Item not found", HttpStatus.NOT_FOUND);
    }
}
