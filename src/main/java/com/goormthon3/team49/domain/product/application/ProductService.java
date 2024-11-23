package com.goormthon3.team49.domain.product.application;

import com.goormthon3.team49.domain.product.domain.entity.Product;
import com.goormthon3.team49.domain.product.domain.entity.ProductReservation;
import com.goormthon3.team49.domain.product.presentation.dto.ParticipantDto;
import com.goormthon3.team49.domain.product.presentation.dto.ProductDto;
import com.goormthon3.team49.domain.product.presentation.dto.SummaryProductDto;
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
    public List<SummaryProductDto> getRecentProducts() {
        return productRepository.findTop3ByOrderByCreatedAtDesc()
                .stream()
                .map(SummaryProductDto::fromEntity)
                .collect(Collectors.toList());
    }

    // 사용자 관련 상품 조회
    public List<SummaryProductDto> getUserRelatedProducts(Long userId) {
        return productRepository.findUserRelatedProductsByUser_UserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(SummaryProductDto::fromEntity)
                .collect(Collectors.toList());
    }

    //전체 상품 조회
    public List<SummaryProductDto> getAllProducts() {
        return productRepository.findAll() // 전체 상품 조회
                .stream()
                .map(SummaryProductDto::fromEntity)
                .collect(Collectors.toList()); // 리스트로 변환
    }

    public ProductDto getProductById(Long productId) {
        // Product 조회
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 첫 번째 예약 정보만 가져옴 (여러 개의 예약이 있을 수 있기 때문에 첫 번째만 선택)
        ProductReservation productReservation = productReservationRepository.findByProduct_ProductId(productId)
                .stream()
                .findFirst()  // 첫 번째 항목만 선택
                .orElse(null); // 예약이 없으면 null 반환

        // ProductDto로 변환하여 반환
        return ProductDto.fromEntity(product, productReservation);
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
