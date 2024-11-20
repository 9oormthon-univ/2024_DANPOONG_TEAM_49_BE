package com.goormthon3.team49.common.exception;
import org.springframework.http.HttpStatus;

public interface ExceptionCode {
    HttpStatus getStatus();

    String getCode();

    String getMessage();
}