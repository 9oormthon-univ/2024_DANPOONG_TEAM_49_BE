package com.goormthon3.team49.domain.product.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ParticipantDto {
    private Long productId;
    private Long userId;
    private String userName;
    private int quantity;
}
