package com.springboot.restful.api.services.exception;

import java.io.Serial;

public class ObjectNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException(String msg) {
        super(msg);
    }


}
