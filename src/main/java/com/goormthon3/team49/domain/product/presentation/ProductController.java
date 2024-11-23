package com.goormthon3.team49.domain.product.presentation;

import com.goormthon3.team49.domain.product.application.ProductService;
import com.goormthon3.team49.domain.product.presentation.dto.ParticipantDto;
import com.goormthon3.team49.domain.product.presentation.dto.ProductDto;
import com.goormthon3.team49.domain.product.presentation.dto.SummaryProductDto;
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
}
