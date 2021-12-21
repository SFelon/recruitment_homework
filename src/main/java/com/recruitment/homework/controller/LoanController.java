package com.recruitment.homework.controller;

import com.recruitment.homework.model.dto.LoanDto;
import com.recruitment.homework.model.enums.LoanType;
import com.recruitment.homework.service.LoanProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/loan", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoanController {

    private final Logger LOGGER = LoggerFactory.getLogger(LoanController.class);
    private final LoanProcessor loanProcessor;

    public LoanController(LoanProcessor loanProcessor) {
        this.loanProcessor = loanProcessor;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoanDto> issueLoan(@RequestBody @Valid LoanDto loanDto) {
        LOGGER.info("New loan request has been submitted, amount: {}, term: {}",
                loanDto.getAmount(), loanDto.getTermInDays());
        return new ResponseEntity<>(loanProcessor.process(loanDto), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoanDto> extendLoan(@PathVariable("id") Long id, @RequestParam(name = "type") LoanType type) {
        LOGGER.info("Extend loan request for id: {} and type: {}", id, type);
        return new ResponseEntity<>(loanProcessor.extend(id, type), HttpStatus.OK);
    }
}
