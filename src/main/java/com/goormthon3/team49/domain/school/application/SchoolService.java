package com.goormthon3.team49.domain.school.application;

import com.goormthon3.team49.domain.school.domain.School;
import com.goormthon3.team49.domain.school.domain.SchoolRepository;
import com.goormthon3.team49.domain.school.presentation.exception.SchoolNotFoundException;
import com.goormthon3.team49.domain.school.presentation.response.SchoolResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    private static final double RADIUS = 10.0; // 반경 10km

    public SchoolResponse findClosestSchool(BigDecimal userLatitude, BigDecimal userLongitude) {
        List<School> schools = schoolRepository.findAll();

        School closestSchool = null;
        double minDistance = Double.MAX_VALUE;

        for (School school : schools) {
            Double distance = calculateDistance(userLatitude, userLongitude, school.getLatitude(), school.getLongitude());

            if (distance <= RADIUS && distance < minDistance) {
                minDistance = distance;
                closestSchool = school;
            }
        }

        if (closestSchool == null) {
            throw new SchoolNotFoundException("반경 " + RADIUS + "km 내에 학교가 없습니다.");
        }

        return SchoolResponse.of(closestSchool.getSchoolName());
    }

    private double calculateDistance(BigDecimal lat1, BigDecimal lon1, BigDecimal lat2, BigDecimal lon2) {
        final int EARTH_RADIUS = 6371; // Radius in kilometers

        MathContext mc = MathContext.DECIMAL64;

        BigDecimal dLat = toRadians(lat2.subtract(lat1, mc), mc);
        BigDecimal dLon = toRadians(lon2.subtract(lon1, mc), mc);

        BigDecimal sinDLat = BigDecimal.valueOf(Math.sin(dLat.doubleValue()));
        BigDecimal sinDLon = BigDecimal.valueOf(Math.sin(dLon.doubleValue()));

        BigDecimal a = sinDLat.multiply(sinDLat, mc)
                .add(
                        cos(toRadians(lat1, mc), mc).multiply(
                                cos(toRadians(lat2, mc), mc), mc
                        ).multiply(
                                sinDLon.multiply(sinDLon, mc), mc
                        ), mc
                );

        BigDecimal c = BigDecimal.valueOf(2).multiply(
                BigDecimal.valueOf(Math.atan2(Math.sqrt(a.doubleValue()), Math.sqrt(1 - a.doubleValue()))), mc
        );

        return EARTH_RADIUS * c.doubleValue(); // Distance in kilometers
    }

    private BigDecimal toRadians(BigDecimal value, MathContext mc) {
        return value.multiply(BigDecimal.valueOf(Math.PI), mc).divide(BigDecimal.valueOf(180), mc);
    }

    private BigDecimal cos(BigDecimal radians, MathContext mc) {
        return BigDecimal.valueOf(Math.cos(radians.doubleValue()));
    }
}
