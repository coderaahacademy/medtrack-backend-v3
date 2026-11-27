package com.coderaah.medtrack.patient.dto;

import com.coderaah.medtrack.patient.domain.AllergySeverity;
import com.coderaah.medtrack.patient.domain.AllergyStatus;

import java.time.LocalDateTime;

public record AllergyResponse(

        Long id,

        String allergen,

        String reaction,

        AllergySeverity severity,

        AllergyStatus status,

        LocalDateTime recordedAt)
 { }
