package com.goormthon3.team49.domain.product.application;

import com.goormthon3.team49.domain.product.domain.entity.Product;
import com.goormthon3.team49.domain.product.domain.entity.Product;
import com.goormthon3.team49.domain.product.domain.entity.ProductReservation;
import com.goormthon3.team49.domain.product.domain.entity.ProductReservationId;
import com.goormthon3.team49.domain.product.presentation.dto.*;
import com.goormthon3.team49.domain.product.domain.repository.ProductRepository;
import com.goormthon3.team49.domain.product.domain.repository.ProductReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    // 상품 등록하기
    public AddProductDto createProduct(Long userId, AddProductRequestDto addProductRequestDto, ProductReservation reservation) {
        // 상품 엔티티 생성
        Product product = new Product();
        product.setTitle(addProductRequestDto.getTitle());
        product.setTotalNum(addProductRequestDto.getTotalNum());
        reservation.setQuantity(addProductRequestDto.getQuantity());
        product.setPrice(addProductRequestDto.getPrice());
        product.setSavePrice(addProductRequestDto.getSavePrice());
        product.setLink(addProductRequestDto.getLink());
        product.setPickupLocation(addProductRequestDto.getPickupLocation());
        // 필요시 이미지 처리 (예: product.setImgId(addProductRequestDto.getImg()));

        // 상품 저장
        Product savedProduct = productRepository.save(product);

        // AddProductDto로 변환하여 반환
        return AddProductDto.fromEntity(savedProduct, reservation);
    }

    // 상품 예약하기 (참여자 등록)
    public void participateProduct(ParticipantDto participantDto) {
        // ProductReservationId 생성 및 설정
        ProductReservationId reservationId = new ProductReservationId();
        reservationId.setProductId(participantDto.getProductId());
        reservationId.setUserId(participantDto.getUserId());

        // ProductReservation 엔티티 생성
        ProductReservation reservation = new ProductReservation();
        reservation.setId(reservationId);  // 복합키 설정
        reservation.setQuantity(participantDto.getQuantity());  // 예약 수량 설정
        //reservation.setRole(participantDto.Role.참여자());
        reservation.setCreatedAt(LocalDateTime.now());  // 생성 일시 설정

        // 예약 정보 저장
        productReservationRepository.save(reservation);
    }
}