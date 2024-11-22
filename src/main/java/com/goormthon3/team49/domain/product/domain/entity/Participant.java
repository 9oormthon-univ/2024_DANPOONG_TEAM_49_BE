package com.goormthon3.team49.domain.product.domain.entity;

import jakarta.persistence.*;

@Entity
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participantId;  // auto increment 컬럼

    private Long userId; // 참여자 ID

    private int quantity; // 참여자가 구매한 수량

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product; // 상품
}
