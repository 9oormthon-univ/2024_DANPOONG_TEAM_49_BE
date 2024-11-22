package com.goormthon3.team49.domain.product.presentation;

import com.goormthon3.team49.domain.product.application.ProductService;
import com.goormthon3.team49.domain.product.presentation.dto.ParticipantDto;
import com.goormthon3.team49.domain.product.presentation.dto.ProductDto;
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
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // 최근 상품 조회
    @GetMapping("/recent")
    public ResponseEntity<List<ProductDto>> getRecentProducts() {
        return ResponseEntity.ok(productService.getRecentProducts());
    }

    // 사용자 관련 상품 조회
    @GetMapping("/{userId}/recent")
    public ResponseEntity<List<ProductDto>> getUserRelatedProducts(@PathVariable Long userId) {
        return ResponseEntity.ok(productService.getUserRelatedProducts(userId));
    }

    // 개별 상품 조회 (주최자)
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

}
