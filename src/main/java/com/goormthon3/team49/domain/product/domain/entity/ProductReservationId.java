package com.goormthon3.team49.domain.product.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Embeddable
public class ProductReservationId implements Serializable {

    @Column(name = "user_id")  // 명시적으로 물리적 컬럼 이름을 지정
    private Long userId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "school_id")
    private Long schoolId;

    // 기본 생성자 (JPA에서 기본 생성자 요구)
    public ProductReservationId() {}

    public ProductReservationId(Long userId, Long productId, Long schoolId) {
        this.userId = userId;
        this.productId = productId;
        this.schoolId = schoolId;
    }

    // equals()와 hashCode() 메서드 오버라이드 (복합키 비교용)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductReservationId that = (ProductReservationId) o;
        return userId.equals(that.userId) && productId.equals(that.productId) && schoolId.equals(that.schoolId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, productId, schoolId);
    }
}
