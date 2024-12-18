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
    @Column(name = "product_id")
    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productImg_id")
    private ProductImage productImage;


    @Column(name = "is_active")
    private boolean isActive;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
