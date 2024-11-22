package com.goormthon3.team49.domain.product.presentation.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
    private String title;
    private Integer totalNum;
    private Integer quantity;
    private Integer price;
    private Integer savePrice;
    private String link;
    private String pickupLocation;
    private String imgUrl;
}
