package com.wolkezoo.plugin.wolkezoo_plugin.even.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String name) {
        super(name);
    }

    public NotFoundException(String name, Exception cause) {
        super(name, cause);
    }
}
