package com.coderaah.medtrack.patient.controller;

import com.coderaah.medtrack.common.exception.GlobalExceptionHandler;
import com.coderaah.medtrack.patient.domain.BloodType;
import com.coderaah.medtrack.patient.dto.PatientRequest;
import com.coderaah.medtrack.patient.dto.PatientResponse;
import com.coderaah.medtrack.patient.exception.PatientNotFoundException;
import com.coderaah.medtrack.patient.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
@Import(GlobalExceptionHandler.class)
class PatientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockitoBean
    PatientService service;

    private final PatientRequest req = new PatientRequest("First", "Last", LocalDate.of(2000, 1, 1), "123", BloodType.O_POSITIVE, "Prov", "Num");
    private final PatientResponse res = new PatientResponse(1L, 1L, "First", "Last", LocalDate.of(2000, 1, 1), "123", "MRN-1", BloodType.O_POSITIVE, "Prov", "Num");

    @Test
    void endpoints_success() throws Exception {
        when(service.createPatient(any())).thenReturn(res);
        when(service.getPatientById(1L)).thenReturn(res);
        when(service.getAllPatients()).thenReturn(List.of(res));
        when(service.updatePatient(eq(1L), any())).thenReturn(res);

        mockMvc.perform(post("/api/patients").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
        mockMvc.perform(get("/api/patients/1")).andExpect(status().isOk());
        mockMvc.perform(get("/api/patients")).andExpect(status().isOk());
        mockMvc.perform(put("/api/patients/1").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    void validationAndNotFound_failure() throws Exception {
        PatientRequest invalid = new PatientRequest("A".repeat(105), "", null, null, null, null, null);
        when(service.getPatientById(99L)).thenThrow(new PatientNotFoundException(99L));

        mockMvc.perform(post("/api/patients").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
        mockMvc.perform(get("/api/patients/99")).andExpect(status().isNotFound());
    }}