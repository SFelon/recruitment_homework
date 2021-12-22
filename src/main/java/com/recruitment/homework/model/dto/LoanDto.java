package com.recruitment.homework.model.dto;

import com.recruitment.homework.model.enums.LoanType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class LoanDto implements Serializable {

    private static final long serialVersionUID = 1836134964094811131L;

    private Long id;
    private final LoanType type = LoanType.DEFAULT;
    @NotNull(message = "loan amount must be set")
    @DecimalMin(value = "0.00", inclusive = false, message = "loan amount must be greater than 0")
    private BigDecimal amount;
    private BigDecimal cost;
    @NotNull(message = "loan term in days must be set")
    @Min(value = 0, message = "loan term must be greater then 0")
    private Integer termInDays;
    private Integer version;

    public Long getId() {
        return id;
    }

    public LoanType getType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount != null ? amount.setScale(2, RoundingMode.HALF_UP)
                : null;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanDto loanDto = (LoanDto) o;
        return Objects.equals(id, loanDto.id) && Objects.equals(version, loanDto.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version);
    }

    @Override
    public String toString() {
        return "LoanDto{" +
                "type=" + type +
                ", amount=" + amount +
                ", cost=" + cost +
                ", termInDays=" + termInDays +
                ", version=" + version +
                '}';
    }
}
