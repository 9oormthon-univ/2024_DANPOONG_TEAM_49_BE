package com.goormthon3.team49.domain.product.presentation;

import com.goormthon3.team49.domain.product.application.ProductService;
import com.goormthon3.team49.domain.product.domain.entity.ProductReservation;
import com.goormthon3.team49.domain.product.presentation.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // 전체 상품 조회
    @GetMapping
    public ResponseEntity<List<SummaryProductDto>> getAllProducts() {
        List<SummaryProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // 최근 상품 조회
    @GetMapping("/recent")
    public ResponseEntity<List<SummaryProductDto>> getRecentProducts() {
        List<SummaryProductDto> recentProducts = productService.getRecentProducts();
        return ResponseEntity.ok(recentProducts);
    }

    // 사용자 관련 상품 조회
    @GetMapping("/{userId}/recent")
    public ResponseEntity<List<SummaryProductDto>> getUserRelatedProducts(@PathVariable Long userId) {
        List<SummaryProductDto> userRelatedProducts = productService.getUserRelatedProducts(userId);
        return ResponseEntity.ok(userRelatedProducts);
    }

    // 개별 상품 조회(상세 조회)
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
        ProductDto product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    // 개별 상품 참여자 조회
    @GetMapping("/{productId}/participant")
    public ResponseEntity<List<ParticipantDto>> getProductParticipants(@PathVariable Long productId) {
        List<ParticipantDto> participants = productService.getProductParticipants(productId);
        return ResponseEntity.ok(participants);
    }

    //상품 등록하기
    @PostMapping("/{userId}/leadCreate")
    public ResponseEntity<AddProductDto> createProduct(@PathVariable Long userId, @RequestBody AddProductRequestDto addProductRequestDto) {
        // AddProductRequestDto에서 ProductReservation 추출
        ProductReservation reservation = addProductRequestDto.getReservation(); // getReservation() 메서드는 AddProductRequestDto에 정의되어 있어야 합니다.

        // ProductService 호출 시 ProductReservation 객체 전달
        AddProductDto createdProduct = productService.createProduct(userId, addProductRequestDto, reservation);

        return ResponseEntity.ok(createdProduct);
    }


    // 상품 구매하기 (예약하기)
    @PostMapping("/participantCreate")
    public ResponseEntity<String> participateProduct(@RequestBody ParticipantDto participantDto) {
        productService.participateProduct(participantDto);
        return ResponseEntity.ok("ok");
    }
}
