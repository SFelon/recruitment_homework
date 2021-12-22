package com.recruitment.homework.service.strategy;

import com.recruitment.homework.model.dto.LoanInDto;
import com.recruitment.homework.model.dto.LoanOutDto;
import com.recruitment.homework.model.entity.Loan;
import com.recruitment.homework.model.entity.LoanProperties;
import com.recruitment.homework.model.enums.LoanType;
import com.recruitment.homework.repository.LoanPropertiesRepository;
import com.recruitment.homework.repository.LoanRepository;
import com.recruitment.homework.service.builder.LoanBuilder;
import com.recruitment.homework.service.validator.DefaultLoanValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class DefaultLoanStrategy extends AbstractLoanStrategy implements ExtendableLoanStrategy {

    private final Logger LOGGER = LoggerFactory.getLogger(AbstractLoanStrategy.class);
    private final static LoanType LOAN_TYPE = LoanType.DEFAULT;

    public DefaultLoanStrategy(LoanRepository loanRepository, LoanPropertiesRepository loanPropertiesRepository) {
        super(loanRepository, loanPropertiesRepository, new DefaultLoanValidator());
    }

    @Override
    public boolean canProcess(LoanType loanType) {
        return LOAN_TYPE == loanType;
    }

    @Override
    public LoanOutDto issueLoan(LoanInDto loanInDto) {
        final LoanProperties loanProperties = loadLoanProperties(LOAN_TYPE);

        final Loan persistedLoan = calculateAndCreate(loanProperties, loanInDto);

        return convertToDto(persistedLoan);
    }

    @Override
    @Transactional
    public LoanOutDto extendLoan(Long id) {
        final LoanProperties loanProperties = loadLoanProperties(LOAN_TYPE);
        final Loan loan = getLoan(id);

        final Loan updatedLoan = extendAndUpdate(loanProperties, loan);

        return convertToDto(updatedLoan);
    }

    private Loan extendAndUpdate(LoanProperties loanProperties, Loan loan) {
        final LoanBuilder builder = getBuilder(loan);
        builder.term(loan.getTermInDays() + loanProperties.getTermRenewalDays());
        final Loan loanToUpdate = builder.build();
        LOGGER.info("Extending loan with id: {} for: {} days", loanToUpdate.getId(), loanProperties.getTermRenewalDays());
        return persistLoan(loanToUpdate);
    }

    private Loan calculateAndCreate(LoanProperties loanProperties, LoanInDto loanInDto) {
        final BigDecimal loanCost = calculateLoanCost(loanProperties, loanInDto.getAmount());
        final Loan newLoan = createLoan(loanProperties, loanInDto, loanCost);
        LOGGER.info("Persisting newly submitted loan for amount: {} and term: {}",
                newLoan.getAmount(), newLoan.getTermInDays());
        return persistLoan(newLoan);
    }

    private BigDecimal calculateLoanCost(LoanProperties loanProperties, BigDecimal amount) {
        return amount.multiply(loanProperties.getPercentageCost()).setScale(2, RoundingMode.HALF_UP);
    }

    private Loan createLoan(LoanProperties loanProperties, LoanInDto loanInDto, BigDecimal loanCost) {
        final LoanBuilder builder = getBuilder();
        builder.amount(loanInDto.getAmount());
        builder.term(loanInDto.getTermInDays());
        builder.cost(loanCost);
        builder.properties(loanProperties);
        return builder.build();
    }
}
