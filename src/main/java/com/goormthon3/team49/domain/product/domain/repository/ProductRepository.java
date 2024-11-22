package com.goormthon3.team49.domain.product.domain.repository;

import com.goormthon3.team49.domain.product.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // 최근 등록된 3개의 상품 조회 (createdAt을 기준으로 정렬)
    List<Product> findTop3ByOrderByCreatedAtDesc();

    // 특정 유저가 주최한 상품 조회 (주최자 userId 기준)
    List<Product> findUserRelatedProductsByUser_UserIdOrderByCreatedAtDesc(Long userId);

    // 전체 상품 조회
    List<Product> findAllByOrderByCreatedAtDesc();

    // 특정 상품 조회
    Product findByProductId(Long productId);
}
