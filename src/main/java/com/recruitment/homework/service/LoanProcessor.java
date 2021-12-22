package com.recruitment.homework.service;

import com.recruitment.homework.model.dto.LoanInDto;
import com.recruitment.homework.model.dto.LoanOutDto;
import com.recruitment.homework.model.enums.LoanType;
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

    public LoanOutDto process(LoanInDto loanDto) {
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

        return selectedStrategyOpt.get().issueLoan(loanDto);
    }

    public LoanOutDto extend(Long id, LoanType type) {
        if (extendableLoanStrategies.isEmpty()) {
            throw new UnsupportedOperationException("No loan extending strategies found");
        }

        final Optional<ExtendableLoanStrategy> selectedStrategyOpt = extendableLoanStrategies
                .stream()
                .filter(loanStrategy -> loanStrategy.canProcess(type))
                .findFirst();

        if (selectedStrategyOpt.isEmpty()) {
            throw new UnsupportedOperationException("No loan extending strategies found for type: " + type);
        }

        return selectedStrategyOpt.get().extendLoan(id);
    }
}
