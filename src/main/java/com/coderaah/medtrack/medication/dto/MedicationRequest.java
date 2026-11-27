package com.coderaah.medtrack.medication.dto;

import com.coderaah.medtrack.medication.domain.DosageForm;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MedicationRequest(
        @NotBlank(message = "generic name is required")
        @Size(max=255, message="generic name must be at most 255 character")
        String genericName,

        @Size(max=255, message="brand name must be at most 255 character")
        String brandName,

        @NotBlank(message = "strength is required")
        @Size(max=50, message="Strength must be at most 50 character")
        String strength,

        @NotBlank (message= "strength unit is required")
        @Size ( max = 50)
        String strengthUnit,

        @NotNull(message= "dosage form is required")
        DosageForm dosageForm,

        @Size ( max = 255)
        String manufacturer)
{
}
