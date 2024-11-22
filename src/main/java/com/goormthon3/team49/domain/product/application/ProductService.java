package com.goormthon3.team49.domain.product.application;

import com.goormthon3.team49.domain.product.presentation.dto.ProductDto;
import com.goormthon3.team49.domain.product.domain.repository.ProductRepository;
import com.goormthon3.team49.domain.product.domain.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ParticipantRepository participantRepository;

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
}
