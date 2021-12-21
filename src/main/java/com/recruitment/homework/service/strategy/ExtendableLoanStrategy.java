package com.recruitment.homework.service.strategy;

import com.recruitment.homework.model.dto.LoanDto;
import com.recruitment.homework.model.enums.LoanType;

public interface ExtendableLoanStrategy {
    public boolean canExtend(LoanType loanType);

    public LoanDto extendLoan(Long id);
}
