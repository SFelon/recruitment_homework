package com.recruitment.homework.service.validator;

import com.recruitment.homework.model.entity.Loan;

import javax.validation.Valid;

public interface LoanValidator {
    void validate(@Valid Loan loan);
}
