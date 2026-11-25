package com.coderaah.medtrack.medication.dto;

import com.coderaah.medtrack.medication.domain.DosageForm;

import java.time.LocalDateTime;

public record MedicationResponse(
        Long id,
        String genericName,
        String brandName,
        String strength,
        String strengthUnit,
        DosageForm dosageForm,
        String manufacturer,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
