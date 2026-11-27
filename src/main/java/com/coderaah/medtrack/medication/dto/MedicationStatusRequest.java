package com.coderaah.medtrack.medication.dto;

import jakarta.validation.constraints.NotNull;

public record MedicationStatusRequest(
        @NotNull
        Boolean active
) {
}
