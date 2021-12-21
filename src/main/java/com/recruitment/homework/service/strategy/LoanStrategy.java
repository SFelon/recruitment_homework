package com.recruitment.homework.service.strategy;

import com.recruitment.homework.model.dto.LoanDto;
import com.recruitment.homework.model.enums.LoanType;

public interface LoanStrategy {
    public boolean canProcess(LoanType loanType);

    public LoanDto execute(LoanDto loanDto);
}
