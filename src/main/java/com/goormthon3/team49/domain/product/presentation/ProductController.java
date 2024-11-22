package com.goormthon3.team49.domain.product.presentation;

import com.goormthon3.team49.domain.product.application.ProductService;
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


}
