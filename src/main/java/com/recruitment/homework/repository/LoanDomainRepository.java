package com.recruitment.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanDomainRepository<T, ID>  extends JpaRepository<T, ID> {
}
