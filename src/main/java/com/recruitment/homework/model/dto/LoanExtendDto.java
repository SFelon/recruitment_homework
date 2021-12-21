package com.recruitment.homework.model.dto;

import com.recruitment.homework.model.enums.LoanType;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class LoanExtendDto implements Serializable {

    private static final long serialVersionUID = -3581044014885975876L;

    @NotNull(message = "loan id must be set")
    private Long id;
    @NotNull(message = "loan version must be set")
    private Integer version;
    private final LoanType type = LoanType.DEFAULT;

    public Long getId() {
        return id;
    }

    public LoanType getType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
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
        LoanExtendDto loanDto = (LoanExtendDto) o;
        return Objects.equals(id, loanDto.id) && Objects.equals(version, loanDto.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version);
    }

    @Override
    public String toString() {
        return "LoanExtendDto{" +
                "id=" + id +
                ", version=" + version +
                ", type=" + type +
                '}';
    }
}
