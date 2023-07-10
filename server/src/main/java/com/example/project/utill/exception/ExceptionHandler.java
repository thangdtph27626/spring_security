package com.example.project.utill.exception;

public abstract class ExceptionHandler<T, Z extends Exception> {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public T handle(Z ex) {
        log(ex);
        return wrap(ex);
    }

    protected abstract T wrap(Z ex);

    private void log(Z ex) {
        System.out.println(ex.getMessage());
    }
}
