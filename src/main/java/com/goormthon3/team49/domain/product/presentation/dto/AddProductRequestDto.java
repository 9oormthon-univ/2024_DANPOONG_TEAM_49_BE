package com.goormthon3.team49.domain.product.presentation.dto;

import com.goormthon3.team49.domain.product.domain.entity.ProductReservation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductRequestDto {
    private String title;
    private Integer totalNum;
    private Integer quantity;
    private Integer price;
    private Integer savePrice;
    private String link;
    private String pickupLocation;
    private String img; // 이미지 주소

    private ProductReservation reservation;

}
