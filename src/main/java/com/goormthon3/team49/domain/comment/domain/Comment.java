package com.goormthon3.team49.domain.comment.domain;

import com.goormthon3.team49.common.domain.BaseTimeEntity;
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

    @Column(nullable = false)
    private Long user_id;

    @Column(nullable = false, length = 2000)
    private String username;

    @Column(nullable = false)
    private Long productId;

    public static Comment create(String content, Long user_id, String username, Long product_id) {
        return Comment.builder()
                .content(content)
                .user_id(user_id)
                .username(username)
                .productId(product_id)
                .build();
    }

}
