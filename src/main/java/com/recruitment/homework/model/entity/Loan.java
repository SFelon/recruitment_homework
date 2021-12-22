package com.recruitment.homework.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "LOAN")
public class Loan implements VersionedEntity {

    private static final long serialVersionUID = -7371801500668672288L;

    @Id
    @GeneratedValue(generator = "LOAN_SG")
    @GenericGenerator(
            name = "LOAN_SG",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator"
    )
    private Long id;

    @NotNull(message = "loan amount must be set")
    @Column(name = "AMOUNT", precision = 10 + 2, scale = 2)
    private BigDecimal amount;

    @Column(name = "COST", precision = 10 + 2, scale = 2)
    private BigDecimal cost;

    @NotNull(message = "loan term in days must be set")
    @Column(name = "TERM")
    private Integer termInDays;

    @NotNull(message = "loan properties must be set")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOAN_PROPERTIES_ID")
    private LoanProperties loanProperties;

    @Version
    private Integer version;

    @Override
    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getTermInDays() {
        return termInDays;
    }

    public void setTermInDays(Integer termInDays) {
        this.termInDays = termInDays;
    }

    public LoanProperties getLoanProperties() {
        return loanProperties;
    }

    public void setLoanProperties(LoanProperties loanProperties) {
        this.loanProperties = loanProperties;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(id, loan.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Loan{" +
                "amount=" + amount +
                ", cost=" + cost +
                ", termInDays=" + termInDays +
                ", version=" + version +
                '}';
    }
}
