package com.recruitment.homework.model.entity;

import com.recruitment.homework.model.enums.LoanType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Objects;


@Entity
@Table(name = "LOAN_PROPERTIES")
@SequenceGenerator(name = "SG_LOAN_PROPERTIES")
public class LoanProperties implements HasId {

    private static final long serialVersionUID = 8697576799470996039L;

    @Id
    @GeneratedValue(generator = "loan-properties-sg")
    @GenericGenerator(
            name = "loan-properties-sg",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator"
    )
    private Long id;

    @NotNull(message = "loan properties type must be set")
    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private LoanType type;

    @NotNull(message = "loan properties percentage cost must be set")
    @Column(name = "PERCENTAGE_COST")
    private BigDecimal percentageCost;

    @NotNull(message = "loan properties min amount must be set")
    @Column(name = "MIN_AMOUNT")
    private BigDecimal minAmount;

    @NotNull(message = "loan properties max amount must be set")
    @Column(name = "MAX_AMOUNT")
    private BigDecimal maxAmount;

    @NotNull(message = "loan properties min term days must be set")
    @Column(name = "MIN_TERM")
    private Integer minTermDays;

    @NotNull(message = "loan properties max term days must be set")
    @Column(name = "MAX_TERM")
    private Integer maxTermDays;

    @Column(name = "TERM_RENEWAL")
    private Integer termRenewalDays;

    @Column(name = "REJECTION_TIME_FROM")
    private LocalTime rejectionTimeFrom;

    @Column(name = "REJECTION_TIME_TO")
    private LocalTime rejectionTimeTo;

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

    public BigDecimal getPercentageCost() {
        return percentageCost;
    }

    public void setPercentageCost(BigDecimal percentageCost) {
        this.percentageCost = percentageCost;
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

    public Integer getMinTermDays() {
        return minTermDays;
    }

    public void setMinTermDays(Integer minTermDays) {
        this.minTermDays = minTermDays;
    }

    public Integer getMaxTermDays() {
        return maxTermDays;
    }

    public void setMaxTermDays(Integer maxTermDays) {
        this.maxTermDays = maxTermDays;
    }

    public Integer getTermRenewalDays() {
        return termRenewalDays;
    }

    public void setTermRenewalDays(Integer termRenewalDays) {
        this.termRenewalDays = termRenewalDays;
    }

    public LocalTime getRejectionTimeFrom() {
        return rejectionTimeFrom;
    }

    public void setRejectionTimeFrom(LocalTime rejectionTimeFrom) {
        this.rejectionTimeFrom = rejectionTimeFrom;
    }

    public LocalTime getRejectionTimeTo() {
        return rejectionTimeTo;
    }

    public void setRejectionTimeTo(LocalTime rejectionTimeTo) {
        this.rejectionTimeTo = rejectionTimeTo;
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
                ", percentageCost=" + percentageCost +
                ", minAmount=" + minAmount +
                ", maxAmount=" + maxAmount +
                ", minTermDays=" + minTermDays +
                ", maxTermDays=" + maxTermDays +
                ", termRenewalDays=" + termRenewalDays +
                ", rejectionTimeFrom=" + rejectionTimeFrom +
                ", rejectionTimeTo=" + rejectionTimeTo +
                '}';
    }
}
