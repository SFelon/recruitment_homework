package com.recruitment.homework.service.strategy;

import com.recruitment.homework.model.dto.LoanOutDto;

public interface ExtendableLoanStrategy extends LoanStrategy {
    LoanOutDto extendLoan(Long id);
}
