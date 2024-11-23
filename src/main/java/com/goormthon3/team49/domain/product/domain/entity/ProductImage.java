package com.goormthon3.team49.domain.product.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productImgId;

    @Column(nullable = false, length = 255)
    private String s3Key;


    public ProductImage() {
    }

    // 생성자
    public ProductImage(String s3Key) {
        this.s3Key = s3Key;
    }

}
