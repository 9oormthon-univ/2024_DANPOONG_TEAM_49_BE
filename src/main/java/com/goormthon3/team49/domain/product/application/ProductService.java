package com.goormthon3.team49.domain.product.application;

import com.goormthon3.team49.domain.product.domain.entity.Product;
import com.goormthon3.team49.domain.product.domain.entity.ProductReservation;
import com.goormthon3.team49.domain.product.presentation.dto.ParticipantDto;
import com.goormthon3.team49.domain.product.presentation.dto.ProductDto;
import com.goormthon3.team49.domain.product.domain.repository.ProductRepository;
import com.goormthon3.team49.domain.product.domain.repository.ProductReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductReservationRepository productReservationRepository;

    // 최근 상품 조회
    public List<ProductDto> getRecentProducts() {
        return productRepository.findTop3ByOrderByCreatedAtDesc()
                .stream()
                .map(ProductDto::fromEntity)
                .collect(Collectors.toList());
    }

    // 사용자 관련 상품 조회
    public List<ProductDto> getUserRelatedProducts(Long userId) {
        return productRepository.findUserRelatedProductsByUser_UserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(ProductDto::fromEntity)
                .collect(Collectors.toList());
    }

    //전체 상품 조회
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll() // 전체 상품 조회
                .stream()
                .map(ProductDto::fromEntity)
                .collect(Collectors.toList()); // 리스트로 변환
    }

    // 개별 상품 조회 (주최자)
    public ProductDto getProductById(Long productId) {
        return productRepository.findById(productId)
                .map(ProductDto::fromEntity)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // 개별 상품 참여자 조회
    public List<ParticipantDto> getProductParticipants(Long productId) {
        List<ProductReservation> reservations = productReservationRepository.findByProduct_ProductId(productId);

        return reservations.stream()
                .map(reservation -> new ParticipantDto(
                        reservation.getProduct().getProductId(),  // productId
                        reservation.getUserId(),                    // userId
                        reservation.getUserName(),                  // userName
                        reservation.getQuantity()                   // quantity
                ))
                .collect(Collectors.toList());
    }
}
