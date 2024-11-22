package com.goormthon3.team49.domain.product.domain.entity;

import com.goormthon3.team49.domain.school.domain.School;
import com.goormthon3.team49.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")  // 컬럼 이름 명시
    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")  // school_id 외래 키 설정
    private School school;

    private String title;
    private int totalNum;
    private int currentNum;
    private int price;
    private int savePrice;

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    private String link;
    private String pickupLocation;

    private Long productImgId;
    private String s3Key;

    @Column(name = "is_active")
    private boolean isActive;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
