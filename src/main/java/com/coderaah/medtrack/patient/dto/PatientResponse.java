package com.coderaah.medtrack.patient.dto;

import com.coderaah.medtrack.patient.domain.BloodType;
import java.time.LocalDate;

public record PatientResponse(
        Long id,
        Long personId,
        String firstName,
        String lastName,
        LocalDate birthDate,
        String phone,
        String medicalRecordNumber,
        BloodType bloodType,
        String insuranceProvider,
        String insuranceNumber
) {}