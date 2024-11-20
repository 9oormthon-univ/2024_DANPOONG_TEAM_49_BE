package com.goormthon3.team49.domain.school.infrastructure;

import com.goormthon3.team49.domain.school.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSchoolRepository extends JpaRepository<School, Long> {
}
