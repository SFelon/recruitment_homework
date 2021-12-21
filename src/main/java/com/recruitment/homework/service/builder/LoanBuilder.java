package com.recruitment.homework.service.builder;

import com.recruitment.homework.model.entity.Loan;
import com.recruitment.homework.model.entity.LoanProperties;
import com.recruitment.homework.service.validator.LoanValidator;

import java.math.BigDecimal;

public class LoanBuilder {
    private final Loan loan;
    private final LoanValidator loanValidator;

    public LoanBuilder(LoanValidator loanValidator) {
        loan = new Loan();
        this.loanValidator = loanValidator;
    }

    public LoanBuilder(Loan loan, LoanValidator loanValidator) {
        this.loan = loan;
        this.loanValidator = loanValidator;
    }

    public LoanBuilder amount(BigDecimal amount) {
        loan.setAmount(amount);
        return this;
    }

    public LoanBuilder cost(BigDecimal cost) {
        loan.setCost(cost);
        return this;
    }

    public LoanBuilder term(Integer term) {
        loan.setTermInDays(term);
        return this;
    }

    public LoanBuilder properties(LoanProperties properties) {
        loan.setLoanProperties(properties);
        return this;
    }

    public Loan build() {
//        Assert.notNull(this.loan.getAmount(), "Loan amount must be set");
//        Assert.notNull(this.loan.getCost(), "Loan cost must be set");
//        Assert.notNull(this.loan.getTermInDays(), "Loan term must be set");
//        Assert.notNull(this.loan.getLoanProperties(), "Loan properties must be set");
        this.loanValidator.validate(this.loan);
        return this.loan;
    }
}
