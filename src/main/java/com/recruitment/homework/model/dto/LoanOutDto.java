package com.recruitment.homework.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class LoanOutDto implements Serializable {

    private static final long serialVersionUID = 1836134964094811131L;

    private Long id;
    private BigDecimal amount;
    private BigDecimal cost;
    private Integer termInDays;
    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        LoanOutDto that = (LoanOutDto) o;
        return Objects.equals(id, that.id) && Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version);
    }

    @Override
    public String toString() {
        return "LoanOutDto{" +
                "id=" + id +
                ", amount=" + amount +
                ", cost=" + cost +
                ", termInDays=" + termInDays +
                ", version=" + version +
                '}';
    }
}
