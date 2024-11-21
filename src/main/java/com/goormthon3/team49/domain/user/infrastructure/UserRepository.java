package com.goormthon3.team49.domain.user.infrastructure;

import com.goormthon3.team49.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByKakaoUserId(Long kakaoUserId);
}
