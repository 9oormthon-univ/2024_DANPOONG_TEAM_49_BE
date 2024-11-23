package com.goormthon3.team49.domain.product.presentation.dto;

import com.goormthon3.team49.domain.product.domain.entity.Product;

import com.goormthon3.team49.domain.product.domain.entity.ProductReservation;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductDto {
    private Long productId;
    private String title;
    private Integer totalNum;
    private Integer quantity;
    private Integer price;
    private Integer savePrice;
    private String link;
    private String pickupLocation;
    private String img; // 이미지 주소



    @Transactional
    public static AddProductDto fromEntity(Product product, ProductReservation reservation) {
        AddProductDto dto = new AddProductDto();
        dto.setProductId(product.getProductId());
        dto.setTitle(product.getTitle());
        dto.setTotalNum(product.getTotalNum());
        dto.setQuantity(reservation.getQuantity());
        dto.setPrice(product.getPrice());
        dto.setSavePrice(product.getSavePrice());
        dto.setLink(product.getLink());
        dto.setPickupLocation(product.getPickupLocation());
//        dto.setImg(product.getProductImgId()); // 이미지 주소
        return dto;
    }

}
