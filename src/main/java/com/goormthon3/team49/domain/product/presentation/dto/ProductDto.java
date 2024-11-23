package com.goormthon3.team49.domain.product.presentation.dto;

import com.goormthon3.team49.domain.product.domain.entity.Product;
import com.goormthon3.team49.domain.product.domain.entity.ProductReservation;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long productId;
    private Long userId;
    private String userName;
    private String title;
    private int totalNum;
    private int quantity;
    private int savePrice;
    private String link;
    private String pickupLocation;
    private Long productImgId;
    private String s3Key;

    public static ProductDto fromEntity(Product product, ProductReservation productReservation) {
        return ProductDto.builder()
                .productId(product.getProductId())
                .userId(product.getUser() != null ? product.getUser().getUserId() : null)
                .userName(product.getUser() != null ? product.getUser().getUserName() : "Unknown User")
                .title(product.getTitle())
                .totalNum(product.getTotalNum())
                .quantity(productReservation != null ? productReservation.getQuantity() : 0)
                .savePrice(product.getSavePrice())
                .link(product.getLink())
                .pickupLocation(product.getPickupLocation())
                .productImgId(product.getProductImage() != null ? product.getProductImage().getProductImgId() : null)
                .s3Key(product.getProductImage() != null ? product.getProductImage().getS3Key() : null)
                .build();
    }
}
