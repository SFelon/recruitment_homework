package com.recruitment.homework.service;

import com.recruitment.homework.model.dto.LoanDto;
import com.recruitment.homework.model.dto.LoanExtendDto;
import com.recruitment.homework.service.strategy.ExtendableLoanStrategy;
import com.recruitment.homework.service.strategy.LoanStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanProcessor {

    private final List<LoanStrategy> loanStrategies;
    private final List<ExtendableLoanStrategy> extendableLoanStrategies;

    public LoanProcessor(List<LoanStrategy> loanStrategies, List<ExtendableLoanStrategy> extendableLoanStrategies) {
        this.loanStrategies = loanStrategies;
        this.extendableLoanStrategies = extendableLoanStrategies;
    }

    public LoanDto process(LoanDto loanDto) {
        if (loanStrategies.isEmpty()) {
            throw new UnsupportedOperationException("No loan issuing strategies found");
        }

        final Optional<LoanStrategy> selectedStrategyOpt = loanStrategies
                .stream()
                .filter(loanStrategy -> loanStrategy.canProcess(loanDto.getType()))
                .findFirst();

        if (selectedStrategyOpt.isEmpty()) {
            throw new UnsupportedOperationException("No loan issuing strategies found for type: " + loanDto.getType());
        }

        return selectedStrategyOpt.get().execute(loanDto);
    }

    public LoanDto extend(LoanExtendDto loanExtendDto) {
        if (extendableLoanStrategies.isEmpty()) {
            throw new UnsupportedOperationException("No loan extending strategies found");
        }

        final Optional<ExtendableLoanStrategy> selectedStrategyOpt = extendableLoanStrategies
                .stream()
                .filter(loanStrategy -> loanStrategy.canExtend(loanExtendDto.getType()))
                .findFirst();

        if (selectedStrategyOpt.isEmpty()) {
            throw new UnsupportedOperationException("No loan extending strategies found for type: " + loanExtendDto.getType());
        }

        return selectedStrategyOpt.get().extendLoan(loanExtendDto.getId(), loanExtendDto.getVersion());
    }
}
