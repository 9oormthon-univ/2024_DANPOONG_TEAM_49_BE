package com.goormthon3.team49.domain.product.domain.repository;

import com.goormthon3.team49.domain.product.domain.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    // 특정 상품에 참여한 사용자 목록을 조회하는 쿼리 (수정)
    List<Participant> findByProduct_ProductId(Long productId);  // 수정된 메소드

    // 특정 사용자와 특정 상품에 대한 참여 내역 조회
    List<Participant> findByUserIdAndProduct_ProductId(Long userId, Long productId);  // 수정된 메소드

    // 특정 상품에 참여한 사용자 수를 조회하는 메소드
    long countByProduct_ProductId(Long productId);  // 수정된 메소드

    // 특정 사용자에 의해 참여한 모든 상품 조회
    List<Participant> findByUserId(Long userId);
}
