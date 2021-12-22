package com.recruitment.homework.repository;

import com.recruitment.homework.model.entity.LoanProperties;
import com.recruitment.homework.model.enums.LoanType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LoanPropertiesRepository extends CrudRepository<LoanProperties, Long> {

    Optional<LoanProperties> findByType(LoanType type);
}
