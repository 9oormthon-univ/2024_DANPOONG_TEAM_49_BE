package com.goormthon3.team49.domain.school.presentation.exception;

import com.goormthon3.team49.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum SchoolExceptionCode implements ExceptionCode {
    SCHOOL_NOT_FOUND(NOT_FOUND, "반경 10KM 내의 기숙사를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;

    @Override
    public String getCode() {
        return this.name();
    }
}