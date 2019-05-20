package com.manan.groceryhelper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by mananshah on 21/05/19.
 */
public class CategoryExceptionController {
    @ExceptionHandler(value = CategoryNotFoundException.class)
    public ResponseEntity<Object> exception (CategoryNotFoundException exception) {
        return new ResponseEntity<Object>("Grocery Category not found", HttpStatus.NOT_FOUND);
    }
}
