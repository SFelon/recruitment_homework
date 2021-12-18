package com.recruitment.homework.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "LOAN")
@SequenceGenerator(name = "SG_LOAN")
public class Loan implements HasId {

    private static final long serialVersionUID = -7371801500668672288L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SG_LOAN")
    private Long id;

    @NotNull(message = "loan amount cannot be empty")
    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "COST")
    private BigDecimal cost;

    @NotNull(message = "loan date from cannot be empty")
    @Column(name = "DATE_FROM")
    private LocalDate dateFrom;

    @NotNull(message = "loan date to cannot be empty")
    @Column(name = "DATE_TO")
    private LocalDate dateTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_properties_id")
    private LoanProperties loanProperties;

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

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public LoanProperties getLoanProperties() {
        return loanProperties;
    }

    public void setLoanProperties(LoanProperties loanProperties) {
        this.loanProperties = loanProperties;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
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
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}
