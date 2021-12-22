package com.recruitment.homework.service.validator;

import com.recruitment.homework.exception.LoanAppRuntimeException;
import com.recruitment.homework.model.entity.Loan;
import com.recruitment.homework.model.entity.LoanProperties;
import com.recruitment.homework.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalTime;
import java.util.stream.Stream;

import static com.recruitment.homework.utils.TestUtils.bd;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DefaultLoanValidatorTest {

    private DefaultLoanValidator validator;

    @BeforeEach
    void setup() {
        validator = new DefaultLoanValidator();
    }

    @ParameterizedTest
    @MethodSource("provideValidLoanProperties")
    void validationSuccessfulScenarios(LoanProperties loanProperties) {
        final Loan loan = prepareLoan(loanProperties);
        assertDoesNotThrow(() -> validator.validate(loan));
    }

    private static Stream<LoanProperties> provideValidLoanProperties() {
        return Stream.of(props(0, 100, 0, 10, null, null),
                props(100, 1000, 10, 100, null, null),
                props(-1, 150, 0, 100, "01:00:00", "00:30:00"));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidLoanProperties")
    void validationFailedScenarios(LoanProperties loanProperties) {
        final Loan loan = prepareLoan(loanProperties);
        assertThrows(LoanAppRuntimeException.class, () -> validator.validate(loan));
    }

    private static Stream<LoanProperties> provideInvalidLoanProperties() {
        return Stream.of(props(101, 200, 0, 10, null, null),
                props(0, 99, 0, 10, null, null),
                props(0, 200, 11, 30, null, null),
                props(0, 200, 0, 9, null, null),
                props(0, 100, 0, 30, "00:00:00", "23:59:59"));
    }

    private Loan prepareLoan(LoanProperties loanProperties) {
        final Loan loan = new Loan();
        loan.setAmount(TestUtils.bd(100));
        loan.setTermInDays(10);
        loan.setLoanProperties(loanProperties);
        return loan;
    }


    private static LoanProperties props(int minAmount, int maxAmount, Integer minTerm, Integer maxTerm,
                                        String rejectionFrom, String rejectionTo) {
        final LoanProperties loanProperties = new LoanProperties();
        loanProperties.setMinAmount(TestUtils.bd(minAmount));
        loanProperties.setMaxAmount(TestUtils.bd(maxAmount));
        loanProperties.setMinTermDays(minTerm);
        loanProperties.setMaxTermDays(maxTerm);
        loanProperties.setRejectionTimeFrom(rejectionFrom != null ? LocalTime.parse(rejectionFrom) : null);
        loanProperties.setRejectionTimeTo(rejectionTo != null ? LocalTime.parse(rejectionTo) : null);
        return loanProperties;
    }
}