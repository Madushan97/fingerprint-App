package com.cba.mpos.aoer.exception;

public class ClientErrorException extends RuntimeException {
    public ClientErrorException(String msg) {
        super(msg);
    }

    public ClientErrorException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
