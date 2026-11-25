package com.coderaah.medtrack.medication.domain;

import java.time.LocalDateTime;

public record MedicationResponse(
        Long id,
        String genericName,
        String brandName,
        String strength,
        String strengthUnit,
        DosageForm dosageForm,
        String manuFactor,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
