package com.recruitment.homework.service.validator;

import com.recruitment.homework.exception.LoanAppRuntimeException;
import com.recruitment.homework.model.entity.Loan;
import com.recruitment.homework.model.entity.LoanProperties;

import java.math.BigDecimal;
import java.time.LocalTime;

public class DefaultLoanValidator implements LoanValidator {

    @Override
    public void validate(Loan loan) {
        validateMaxAmountWithinRejectionTime(loan.getLoanProperties(), loan);
        validateTermRange(loan.getLoanProperties(), loan.getTermInDays());
        validateAmount(loan.getLoanProperties(), loan.getAmount());
    }

    private void validateMaxAmountWithinRejectionTime(LoanProperties loanProperties, Loan loan) {
        final LocalTime loanRequestTime = LocalTime.now();
        if (isMaxAmountAsked(loanProperties, loan.getAmount()) && isWithinRejectionTime(loanRequestTime, loanProperties)) {
            throw new LoanAppRuntimeException("Cannot issue a loan - maximum amount within rejection time submitted");
        }
    }

    private boolean isMaxAmountAsked(LoanProperties loanProperties, BigDecimal amount) {
        return amount.equals(loanProperties.getMaxAmount());
    }

    private boolean isWithinRejectionTime(LocalTime loanRequestTime, LoanProperties loanProperties) {
        return isEqualOrAfterRejectionTimeFrom(loanRequestTime, loanProperties.getRejectionTimeFrom()) &&
                isEqualOrBeforeRejectionTimeTo(loanRequestTime, loanProperties.getRejectionTimeTo());
    }

    private boolean isEqualOrAfterRejectionTimeFrom(LocalTime loanRequestTime, LocalTime rejectionFrom) {
        return loanRequestTime.equals(rejectionFrom) ||
                loanRequestTime.isAfter(rejectionFrom);
    }

    private boolean isEqualOrBeforeRejectionTimeTo(LocalTime loanRequestTime, LocalTime rejectionTo) {
        return loanRequestTime.equals(rejectionTo) ||
                loanRequestTime.isBefore(rejectionTo);
    }

    private void validateTermRange(LoanProperties loanProperties, Integer termInDays) {
        if (termInDays < loanProperties.getMinTermDays() || termInDays > loanProperties.getMaxTermDays()) {
            throw new LoanAppRuntimeException("Cannot issue a loan - term in days is not within specified min/max range");
        }
    }

    private void validateAmount(LoanProperties loanProperties, BigDecimal amount) {
        if (amount.compareTo(loanProperties.getMinAmount()) < 0 ||
                amount.compareTo(loanProperties.getMaxAmount()) > 0) {
            throw new LoanAppRuntimeException("Cannot issue a loan - amount is not within specified min/max range");
        }
    }
}
