package com.manan.groceryhelper.exception;

/**
 * Created by mananshah on 20/05/19.
 */
public class ItemNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ItemNotFoundException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ItemNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public ItemNotFoundException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public ItemNotFoundException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}
