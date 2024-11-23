package com.goormthon3.team49.domain.comment.domain;

import com.goormthon3.team49.common.domain.BaseTimeEntity;
import com.goormthon3.team49.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2000)
    private String content;

    @ManyToOne
    @JoinColumn
    private User user;

    @Column(nullable = false)
    private Long productId;


    public static Comment create(String content, User user, Long product_id) {
        return Comment.builder()
                .content(content)
                .user(user)
                .productId(product_id)
                .build();
    }

}
