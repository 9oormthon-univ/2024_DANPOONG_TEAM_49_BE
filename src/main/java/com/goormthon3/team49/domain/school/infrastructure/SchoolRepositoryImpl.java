package com.goormthon3.team49.domain.school.infrastructure;

import com.goormthon3.team49.domain.school.domain.School;
import com.goormthon3.team49.domain.school.domain.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SchoolRepositoryImpl implements SchoolRepository {
    private final JpaSchoolRepository jpaschoolRepository;

    @Override
    public List<School> findAll(){
        return jpaschoolRepository.findAll();
    }// 모든 학교 데이터 조회
}
