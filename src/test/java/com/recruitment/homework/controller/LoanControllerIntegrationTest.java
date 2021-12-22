package com.recruitment.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recruitment.homework.model.dto.LoanInDto;
import com.recruitment.homework.model.dto.LoanOutDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static com.recruitment.homework.utils.TestUtils.bd;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LoanControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void issueLoan() throws Exception {
        final LoanInDto dto = new LoanInDto();
        dto.setAmount(BigDecimal.valueOf(1000));
        dto.setTermInDays(150);

        final MvcResult result = mockMvc.perform(post("/loan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn();

        final LoanOutDto responseBody = objectMapper.readValue(result.getResponse().getContentAsString(), LoanOutDto.class);
        assertEquals(bd(1000.00), responseBody.getAmount());
        assertEquals(bd(100.00), responseBody.getCost());
        assertEquals(150, responseBody.getTermInDays());
        assertNotNull(responseBody.getId());
        assertNotNull(responseBody.getVersion());
    }

    @Test
    void extendLoan() throws Exception {
        final LoanInDto dto = new LoanInDto();
        dto.setAmount(BigDecimal.valueOf(1000));
        dto.setTermInDays(150);

        final MvcResult result = mockMvc.perform(post("/loan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn();

        final LoanOutDto createdLoan = objectMapper.readValue(result.getResponse().getContentAsString(), LoanOutDto.class);

        final MvcResult extendResponse = mockMvc.perform(patch("/loan/{id}", createdLoan.getId())
                        .param("type", "DEFAULT"))
                .andExpect(status().isOk())
                .andReturn();

        final LoanOutDto responseBody = objectMapper.readValue(extendResponse.getResponse().getContentAsString(), LoanOutDto.class);
        assertEquals(bd(1000.00), responseBody.getAmount());
        assertEquals(bd(100.00), responseBody.getCost());
        assertEquals(180, responseBody.getTermInDays());
        assertNotNull(responseBody.getId());
        assertNotNull(responseBody.getVersion());
    }
}