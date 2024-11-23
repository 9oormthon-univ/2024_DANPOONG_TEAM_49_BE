package com.goormthon3.team49.domain.product.domain.entity;

import com.goormthon3.team49.domain.school.domain.School;
import com.goormthon3.team49.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity(name = "ProductReservation")
public class ProductReservation {

    @EmbeddedId
    private ProductReservationId id;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "school_id", insertable = false, updatable = false)
    private School school;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;


    private int quantity;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createdAt;

    public enum Role {
        주최자,
        참여자
    }

    public ProductReservation(ProductReservationId id, Product product, School school, User user, int quantity, Role role) {
        this.id = id;
        this.product = product;
        this.school = school;
        this.user = user;
        this.quantity = quantity;
        this.role = role;
        this.createdAt = LocalDateTime.now();
    }


    public ProductReservation() {
    }


    public Long getUserId() {
        return user != null ? user.getUserId() : null;  // User 객체에서 getUserId()를 호출
    }

    public String getUserName() {
        return user != null ? user.getUserName() : null;  // User 객체에서 getUserName()을 호출
    }
}
