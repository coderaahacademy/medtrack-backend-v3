package com.coderaah.medtrack.patient.dto;

import com.coderaah.medtrack.patient.domain.BloodType;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public record PatientRequest(
        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        LocalDate birthDate,
        String phone,
        BloodType bloodType,
        String insuranceProvider,
        String insuranceNumber) {}