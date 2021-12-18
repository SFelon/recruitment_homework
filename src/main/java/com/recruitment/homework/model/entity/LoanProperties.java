package com.recruitment.homework.model.entity;

import com.recruitment.homework.model.enums.LoanType;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "LOAN_PROPERTIES")
@SequenceGenerator(name = "SG_LOAN_PROPERTIES")
public class LoanProperties implements HasId {

    private static final long serialVersionUID = 8697576799470996039L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SG_LOAN_PROPERTIES")
    private Long id;

    @NotNull(message = "loan properties type must be set")
    @NaturalId
    @Column(name = "type", unique = true)
    @Enumerated(EnumType.STRING)
    private LoanType type;

    @NotNull(message = "loan properties min amount must be set")
    @Column(name = "MIN_AMOUNT")
    private BigDecimal minAmount;

    @NotNull(message = "loan properties max amount must be set")
    @Column(name = "MAX_AMOUNT")
    private BigDecimal maxAmount;

    @NotNull(message = "loan properties min date must be set")
    @Column(name = "MIN_DATE")
    private LocalDate minDate;

    @NotNull(message = "loan properties max date must be set")
    @Column(name = "MAX_DATE")
    private LocalDate maxDate;

    @Override
    public Long getId() {
        return id;
    }

    public LoanType getType() {
        return type;
    }

    public void setType(LoanType type) {
        this.type = type;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public LocalDate getMinDate() {
        return minDate;
    }

    public void setMinDate(LocalDate minDate) {
        this.minDate = minDate;
    }

    public LocalDate getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(LocalDate maxDate) {
        this.maxDate = maxDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanProperties that = (LoanProperties) o;
        return Objects.equals(id, that.id) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return "LoanProperties{" +
                "type=" + type +
                ", minAmount=" + minAmount +
                ", maxAmount=" + maxAmount +
                ", minDate=" + minDate +
                ", maxDate=" + maxDate +
                '}';
    }
}
