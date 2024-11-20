package com.goormthon3.team49.domain.school.presentation.exception;


import com.goormthon3.team49.common.exception.CustomException;
import static com.goormthon3.team49.domain.school.presentation.exception.SchoolExceptionCode.SCHOOL_NOT_FOUND;

public class SchoolNotFoundException extends CustomException {
    public SchoolNotFoundException() {
        super(SCHOOL_NOT_FOUND);
    }
}

