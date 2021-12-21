package com.recruitment.homework.repository;

import com.recruitment.homework.model.entity.LoanProperties;
import com.recruitment.homework.model.enums.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanPropertiesRepository extends JpaRepository<LoanProperties, Long> {

    Optional<LoanProperties> findByType(LoanType type);
}
