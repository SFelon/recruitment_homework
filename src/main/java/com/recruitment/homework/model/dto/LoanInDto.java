package com.recruitment.homework.model.dto;

import com.recruitment.homework.model.enums.LoanType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class LoanInDto implements Serializable {

    private static final long serialVersionUID = 2836134964094811131L;

    private final LoanType type = LoanType.DEFAULT;
    @NotNull(message = "loan amount must be set")
    @DecimalMin(value = "0.00", inclusive = false, message = "loan amount must be greater than 0")
    private BigDecimal amount;
    @NotNull(message = "loan term in days must be set")
    @Min(value = 0, message = "loan term must be greater then 0")
    private Integer termInDays;

    public LoanType getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount != null ? amount.setScale(2, RoundingMode.HALF_UP)
                : null;
    }

    public Integer getTermInDays() {
        return termInDays;
    }

    public void setTermInDays(Integer termInDays) {
        this.termInDays = termInDays;
    }

    @Override
    public String toString() {
        return "LoanInDto{" +
                "type=" + type +
                ", amount=" + amount +
                ", termInDays=" + termInDays +
                '}';
    }
}
