package com.goormthon3.team49.domain.product.domain.entity;

import com.goormthon3.team49.domain.school.domain.School;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity(name = "ProductReservation")
public class ProductReservation {

    @EmbeddedId
    private ProductReservationId id;  // 복합키 필드

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "school_id", referencedColumnName = "school_id", insertable = false, updatable = false)
    private School school;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createdAt;

    public enum Role {
        주최자,
        참여자
    }

    // 생성자에서 id를 수동으로 설정할 수 있습니다.
    public ProductReservation(ProductReservationId id, Product product, School school, int quantity, Role role) {
        this.id = id;
        this.product = product;
        this.school = school;
        this.quantity = quantity;
        this.role = role;
        this.createdAt = LocalDateTime.now();  // 생성일시 자동 설정
    }

    // 생성자 없이 기본값을 설정하려면 기본 생성자도 제공해야 합니다.
    public ProductReservation() {
        // 기본 생성자
    }
}
