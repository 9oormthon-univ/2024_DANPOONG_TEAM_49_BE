package com.goormthon3.team49.domain.product.presentation.dto;

import com.goormthon3.team49.domain.product.domain.entity.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummaryProductDto {
    private Long productId;
    private String title;
    private int savePrice;
    private int participantCount;
    private Long productImgId;
    //private String s3Key;

    public static SummaryProductDto fromEntity(Product product) {
        Long productImgId = product.getProductImage() != null ? product.getProductImage().getProductImgId() : null;
        String s3Key = product.getProductImage() != null ? product.getProductImage().getS3Key() : null;

        return SummaryProductDto.builder()
                .productId(product.getProductId())
                .title(product.getTitle())
                .savePrice(product.getSavePrice())
                //.participantCount(product.getParticipants().size())  // 수정 필요
                .productImgId(productImgId)
//                .s3Key(s3Key)
                .build();
    }
}
