package com.goormthon3.team49.domain.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private Long kakaoUserId;

    @Column(length = 20)
    private String userName;

    @Column(length = 10)
    private String school;

    @Column(precision = 9, scale = 6)
    private BigDecimal latitude;
    @Column(precision = 9, scale = 6)
    private BigDecimal longitude;

    private Boolean activated;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @Column(length = 255)
    private String profileImageUri;

    @Column(length = 255)
    private String email;

    @PrePersist
    public void prePersist() {
        if(this.activated == null) {
            this.activated = true;
        }
    }

    public void updateEmail(String email) {
        this.email = email;
    }
}
