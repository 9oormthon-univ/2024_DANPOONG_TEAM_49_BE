package com.goormthon3.team49.domain.school.presentation;

import com.goormthon3.team49.domain.school.application.SchoolService;
import com.goormthon3.team49.domain.school.presentation.response.SchoolResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schools")
public class SchoolController {
    private final SchoolService schoolService;
    @GetMapping
    public ResponseEntity<SchoolResponse> getSchoolsByLocation(
            @RequestParam BigDecimal latitude,
            @RequestParam BigDecimal longitude) {
        SchoolResponse response = schoolService.findClosestSchool(latitude, longitude);
        return ResponseEntity.ok(response);
    }
}
