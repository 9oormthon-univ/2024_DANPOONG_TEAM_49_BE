package com.goormthon3.team49.domain.product.presentation.dto;

import com.goormthon3.team49.domain.product.domain.entity.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long productId;
    private String title;
    private int savePrice;
    private int participantCount;
    private Long productImgId;
    private String s3Key;

    public static ProductDto fromEntity(Product product) {
        return ProductDto.builder()
                .productId(product.getProductId())
                .title(product.getTitle())
                .savePrice(product.getSavePrice())
                //.participantCount(product.getParticipants().size())
                .productImgId(product.getProductImgId())
                .s3Key(product.getS3Key())
                .build();
    }
}
