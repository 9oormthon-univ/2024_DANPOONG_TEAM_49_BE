package com.goormthon3.team49.domain.product.domain.entity;

import com.goormthon3.team49.domain.user.domain.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;  // 상품의 주최자와 연결 (유저)

    private String title;
    private int totalNum;
    private int price;
    private int savePrice;

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    private String link;
    private String pickupLocation;

    private Long productImgId;
    private String s3Key;  // S3 키

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    // Getters and Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSavePrice() {
        return savePrice;
    }

    public void setSavePrice(int savePrice) {
        this.savePrice = savePrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public Long getProductImgId() {
        return productImgId;
    }

    public void setProductImgId(Long productImgId) {
        this.productImgId = productImgId;
    }

    public String getS3Key() {
        return s3Key;
    }

    public void setS3Key(String s3Key) {
        this.s3Key = s3Key;
    }
}
