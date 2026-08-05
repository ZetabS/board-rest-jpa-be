package com.kh.boardrestjpa.exception;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private Logger logger;

    @ExceptionHandler(NotFoundException.class)
    protected ProblemDetail handleNotFoundException(NotFoundException ex, WebRequest request) {
        return createProblemDetail(
                ex,
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                null,
                null,
                request);
    }

    @ExceptionHandler(BadRequestException.class)
    protected ProblemDetail handleNotFoundException(BadRequestException ex, WebRequest request) {
        return createProblemDetail(
                ex,
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                null,
                null,
                request);
    }

    @ExceptionHandler(Exception.class)
    protected ProblemDetail handleUnexpectedException(Exception ex, WebRequest request) {
        logger.error(ex.getMessage());
        ex.printStackTrace();
        return createProblemDetail(
                ex,
                HttpStatus.INTERNAL_SERVER_ERROR,
                "알 수 없는 오류가 발생하였습니다.",
                null,
                null,
                request);
    }
}
