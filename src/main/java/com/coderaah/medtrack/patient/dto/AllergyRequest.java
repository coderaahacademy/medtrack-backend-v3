package com.coderaah.medtrack.patient.dto;

import com.coderaah.medtrack.patient.domain.AllergySeverity;
import com.coderaah.medtrack.patient.domain.AllergyStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AllergyRequest(
        @NotBlank(message = "Allergen is required")
        @Size(max = 255)
        String allergen,

        @Size(max = 255)
        String reaction,

        AllergySeverity severity,

        AllergyStatus status) {
}
