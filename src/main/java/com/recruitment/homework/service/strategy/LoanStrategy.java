package com.recruitment.homework.service.strategy;

import com.recruitment.homework.model.dto.LoanInDto;
import com.recruitment.homework.model.dto.LoanOutDto;
import com.recruitment.homework.model.enums.LoanType;

public interface LoanStrategy {
    boolean canProcess(LoanType loanType);

    LoanOutDto issueLoan(LoanInDto loanInDto);
}
