package com.glessit.neurofunky.web;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
class GlobalControllerExceptionHandler {

    class ErrorInfo {
        public final String url;
        public final String ex;

        public ErrorInfo(String url, Exception ex) {
            this.url = url;
            this.ex = ex.getLocalizedMessage();
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 409
    @ExceptionHandler(Exception.class)
    public ErrorInfo handleConflict(HttpServletRequest req, Exception ex) {
        System.out.println();
        return new ErrorInfo(req.getRequestURI().toLowerCase(), ex);
    }
}
