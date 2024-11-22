package com.goormthon3.team49.domain.product.domain.repository;

import com.goormthon3.team49.domain.product.domain.entity.ProductReservation;
import com.goormthon3.team49.domain.product.domain.entity.ProductReservationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReservationRepository extends JpaRepository<ProductReservation, ProductReservationId> {

    // 특정 상품에 참여한 사용자 목록을 조회하는 쿼리
    List<ProductReservation> findByProduct_ProductId(Long productId);

    // 특정 사용자와 특정 상품에 대한 참여 내역 조회
    List<ProductReservation> findById_UserIdAndId_ProductId(Long userId, Long productId);

    // 특정 상품에 참여한 사용자 수를 조회하는 메소드
    long countByProduct_ProductId(Long productId);

    // 특정 사용자에 의해 참여한 모든 상품 조회
    List<ProductReservation> findById_UserId(Long userId);
}
