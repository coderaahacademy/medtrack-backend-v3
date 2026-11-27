package com.coderaah.medtrack.patient.dto;

import com.coderaah.medtrack.patient.domain.BloodType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record PatientRequest(
        @NotBlank(message = "First name is required")
        @Size(max = 100, message = "First name must not exceed 100 characters")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(max = 100, message = "Last name must not exceed 100 characters")
        String lastName,

        LocalDate birthDate,

        @Size(max = 50, message = "Phone must not exceed 50 characters")
        String phone,

        BloodType bloodType,

        @Size(max = 255, message = "Insurance provider must not exceed 255 characters")
        String insuranceProvider,

        @Size(max = 100, message = "Insurance number must not exceed 100 characters")
        String insuranceNumber) {}