package com.qm.quantitymeasurement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qm.quantitymeasurement.dto.QuantityRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class QuantityControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldAddFeetAndInchesAndReturnCentimeters() throws Exception {
        QuantityRequestDto input = new QuantityRequestDto();
        input.setFirstValue(1.0);
        input.setFirstUnit("FEET");
        input.setSecondValue(2.0);
        input.setSecondUnit("INCH");

        mockMvc.perform(post("/api/v1/quantities/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(35.56))
                .andExpect(jsonPath("$.unit").value("CENTIMETER"));
    }

    @Test
    public void shouldConvertGallonToLitre() throws Exception {
        QuantityRequestDto input = new QuantityRequestDto();
        input.setValue(1.0);
        input.setUnit("GALLON");
        input.setTargetUnit("LITRE");

        mockMvc.perform(post("/api/v1/quantities/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(3.79))
                .andExpect(jsonPath("$.unit").value("LITRE"));
    }

    @Test
    public void shouldSubtractGramAndKilogramAndReturnGrams() throws Exception {
        QuantityRequestDto input = new QuantityRequestDto();
        input.setFirstValue(2000.0);
        input.setFirstUnit("GRAM");
        input.setSecondValue(1.0);
        input.setSecondUnit("KILOGRAM");

        mockMvc.perform(post("/api/v1/quantities/subtract")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(1000.0))
                .andExpect(jsonPath("$.unit").value("GRAM"));
    }

    @Test
    public void shouldDivideLitreAndReturnDouble() throws Exception {
        QuantityRequestDto input = new QuantityRequestDto();
        input.setValue(10.0);
        input.setUnit("LITRE");
        input.setDivisor(2.0);

        mockMvc.perform(post("/api/v1/quantities/divide")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(5.0));
    }

    @Test
    public void shouldCompareEqualCelsiusAndFahrenheit() throws Exception {
        QuantityRequestDto input = new QuantityRequestDto();
        input.setFirstValue(100.0);
        input.setFirstUnit("CELSIUS");
        input.setSecondValue(212.0);
        input.setSecondUnit("FAHRENHEIT");

        mockMvc.perform(post("/api/v1/quantities/compare")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(1.0))
                .andExpect(jsonPath("$.unit").value("EQUAL"));
    }

    @Test
    public void shouldReturnHistoryAndOperationHistory() throws Exception {
        // Run an operation first to ensure history is populated
        QuantityRequestDto input = new QuantityRequestDto();
        input.setFirstValue(1.0);
        input.setFirstUnit("FEET");
        input.setSecondValue(2.0);
        input.setSecondUnit("INCH");

        mockMvc.perform(post("/api/v1/quantities/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/quantities/history"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].operationType").value("ADDITION"));

        mockMvc.perform(get("/api/v1/quantities/history/ADDITION"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void shouldThrowBadRequestWhenAddingDifferentCategories() throws Exception {
        QuantityRequestDto input = new QuantityRequestDto();
        input.setFirstValue(1.0);
        input.setFirstUnit("FEET");
        input.setSecondValue(1.0);
        input.setSecondUnit("LITRE");

        mockMvc.perform(post("/api/v1/quantities/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldThrowBadRequestWhenArithmeticUnsupportedForTemperature() throws Exception {
        QuantityRequestDto input = new QuantityRequestDto();
        input.setFirstValue(100.0);
        input.setFirstUnit("CELSIUS");
        input.setSecondValue(50.0);
        input.setSecondUnit("CELSIUS");

        mockMvc.perform(post("/api/v1/quantities/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isBadRequest());
    }
}
