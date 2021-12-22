package com.recruitment.homework.service.strategy;

import com.recruitment.homework.model.dto.LoanOutDto;
import com.recruitment.homework.model.entity.Loan;
import com.recruitment.homework.model.entity.LoanProperties;
import com.recruitment.homework.model.enums.LoanType;
import com.recruitment.homework.repository.LoanPropertiesRepository;
import com.recruitment.homework.repository.LoanRepository;
import com.recruitment.homework.service.builder.LoanBuilder;
import com.recruitment.homework.service.validator.LoanValidator;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

public abstract class AbstractLoanStrategy {
    private final LoanRepository loanRepository;
    private final LoanPropertiesRepository loanPropertiesRepository;
    private final LoanValidator loanValidator;

    public AbstractLoanStrategy(LoanRepository loanRepository, LoanPropertiesRepository loanPropertiesRepository,
                                LoanValidator loanValidator) {
        this.loanRepository = loanRepository;
        this.loanPropertiesRepository = loanPropertiesRepository;
        this.loanValidator = loanValidator;
    }

    @Transactional(readOnly = true)
    protected LoanProperties loadLoanProperties(LoanType loanType) {
        return loanPropertiesRepository.findByType(loanType)
                .orElseThrow(() -> new EntityNotFoundException("Could not find loan properties for type: " + loanType));
    }

    protected Loan getLoan(Long id) {
        return loanRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find loan entity with id: " + id));
    }

    protected LoanBuilder getBuilder() {
        return new LoanBuilder(loanValidator);
    }

    protected LoanBuilder getBuilder(Loan loan) {
        return new LoanBuilder(loan, loanValidator);
    }

    protected Loan persistLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    protected LoanOutDto convertToDto(Loan persistedLoan) {
        final LoanOutDto dto = new LoanOutDto();
        dto.setId(persistedLoan.getId());
        dto.setAmount(persistedLoan.getAmount());
        dto.setCost(persistedLoan.getCost());
        dto.setTermInDays(persistedLoan.getTermInDays());
        dto.setVersion(persistedLoan.getVersion());
        return dto;
    }
}
