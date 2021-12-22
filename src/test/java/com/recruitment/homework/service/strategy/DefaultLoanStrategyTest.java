package com.recruitment.homework.service.strategy;

import com.recruitment.homework.exception.LoanAppRuntimeException;
import com.recruitment.homework.model.dto.LoanDto;
import com.recruitment.homework.model.entity.Loan;
import com.recruitment.homework.model.entity.LoanProperties;
import com.recruitment.homework.model.enums.LoanType;
import com.recruitment.homework.repository.LoanPropertiesRepository;
import com.recruitment.homework.repository.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static com.recruitment.homework.utils.TestUtils.bd;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultLoanStrategyTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private LoanPropertiesRepository loanPropertiesRepository;

    @Captor
    private ArgumentCaptor<Loan> loanPersistCaptor;

    private DefaultLoanStrategy defaultLoanStrategy;

    @BeforeEach
    void setUp() {
        defaultLoanStrategy = new DefaultLoanStrategy(loanRepository, loanPropertiesRepository);
    }

    @Test
    void issueLoan() {
        //given
        final LoanDto dto = prepareDto();
        final LoanProperties properties = prepareProps();
        when(loanPropertiesRepository.findByType(LoanType.DEFAULT)).thenReturn(Optional.of(properties));
        when(loanRepository.save(loanPersistCaptor.capture())).thenReturn(new Loan());

        //when
        defaultLoanStrategy.issueLoan(dto);

        //then
        verify(loanPropertiesRepository).findByType(LoanType.DEFAULT);
        verify(loanRepository).save(any());
        final Loan persistedLoan = loanPersistCaptor.getValue();
        assertEquals(30, persistedLoan.getTermInDays());
        assertEquals(bd(1000.50), persistedLoan.getAmount());
        assertEquals(bd(100.05), persistedLoan.getCost());
        assertNotNull(persistedLoan.getLoanProperties());
    }

    @Test
    void extendLoanSuccess() {
        //given
        final LoanProperties properties = prepareProps();
        final Loan loan = prepareLoan(properties);
        when(loanPropertiesRepository.findByType(LoanType.DEFAULT)).thenReturn(Optional.of(properties));
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        when(loanRepository.save(loanPersistCaptor.capture())).thenReturn(new Loan());

        //when
        defaultLoanStrategy.extendLoan(1L);

        //then
        verify(loanPropertiesRepository).findByType(LoanType.DEFAULT);
        verify(loanRepository).findById(1L);
        verify(loanRepository).save(any());
        final Loan persistedLoan = loanPersistCaptor.getValue();
        assertEquals(1L, persistedLoan.getId());
        assertEquals(0, persistedLoan.getVersion());
        assertEquals(31, persistedLoan.getTermInDays());
        assertEquals(bd(1000.50), persistedLoan.getAmount());
        assertEquals(bd(100.05), persistedLoan.getCost());
        assertNotNull(persistedLoan.getLoanProperties());
    }

    @Test
    void extendLoanMaxTermExceeded() {
        //given
        final LoanProperties properties = prepareProps();
        final Loan loan = prepareLoan(properties);
        loan.setTermInDays(31);
        when(loanPropertiesRepository.findByType(LoanType.DEFAULT)).thenReturn(Optional.of(properties));
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));

        //when-then
        assertThrows(LoanAppRuntimeException.class, () -> defaultLoanStrategy.extendLoan(1L));
        verify(loanPropertiesRepository).findByType(LoanType.DEFAULT);
        verify(loanRepository).findById(1L);
    }

    private LoanDto prepareDto() {
        final LoanDto dto = new LoanDto();
        dto.setAmount(bd(1000.50));
        dto.setTermInDays(30);
        return dto;
    }

    private Loan prepareLoan(LoanProperties properties) {
        final Loan loan = new Loan();
        ReflectionTestUtils.setField(loan, "id", 1L);
        ReflectionTestUtils.setField(loan, "version", 0);
        loan.setAmount(bd(1000.50));
        loan.setTermInDays(30);
        loan.setLoanProperties(properties);
        loan.setCost(bd(100.05));
        return loan;
    }

    private LoanProperties prepareProps() {
        final LoanProperties props = new LoanProperties();
        props.setMinAmount(bd(100.50));
        props.setMaxAmount(bd(1000.50));
        props.setMinTermDays(29);
        props.setMaxTermDays(31);
        props.setPercentageCost(bd(0.10));
        props.setTermRenewalDays(1);
        return props;
    }

}