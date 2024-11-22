package com.goormthon3.team49.domain.product;

import com.goormthon3.team49.domain.product.domain.entity.Product;
import com.goormthon3.team49.domain.product.domain.entity.Participant;
import com.goormthon3.team49.domain.product.domain.repository.ProductRepository;
import com.goormthon3.team49.domain.product.domain.repository.ParticipantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestDataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;
//    private final ParticipantRepository participantRepository;

    public TestDataLoader(ProductRepository productRepository) { //ParticipantRepository participantRepository
        this.productRepository = productRepository;
//        this.participantRepository = participantRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 상품 추가 (임의의 상품 생성)
        Product product = new Product();
        product.setTitle("Test Product");
        product.setTotalNum(100);
        product.setPrice(10000);
        product.setSavePrice(5000);
        product.setLink("http://example.com");
        product.setPickupLocation("Test Location");
        // 상품만 저장
        productRepository.save(product); // 저장

//        // 참여자 추가 (임의의 참여자 생성)
//        Participant participant = new Participant();
//        participant.setQuantity(2);
//        participant.setProduct(product); // 상품과 연결
//        // 참여자만 저장
//        participantRepository.save(participant); // 저장
    }
}
