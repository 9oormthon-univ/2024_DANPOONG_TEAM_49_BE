package com.goormthon3.team49.domain.school.presentation.response;

import lombok.Builder;

@Builder
public record SchoolResponse(String school) {
    public static SchoolResponse of(String school) {
        return new SchoolResponse(school);
    }
}
