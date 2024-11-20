package com.goormthon3.team49.domain.school.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class School {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2000)
    private String schoolName;

    @Column(precision = 9, scale = 6)
    private BigDecimal latitude;

    @Column(precision = 9, scale = 6)
    private BigDecimal longitude;


}