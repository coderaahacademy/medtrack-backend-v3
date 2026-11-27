package com.coderaah.medtrack.medication.service;

import com.coderaah.medtrack.medication.domain.Medication;
import com.coderaah.medtrack.medication.dto.MedicationRequest;
import com.coderaah.medtrack.medication.dto.MedicationResponse;
import com.coderaah.medtrack.medication.repository.MedicationRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicationService {
    private final MedicationRepository medicationRepository;


    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;

    }

    @Transactional
    public MedicationResponse createMedication(MedicationRequest request){
        Medication medication = medicationRepository.save(new Medication(request.genericName(),
                request.brandName(),
                request.strength(),
                request.strengthUnit(),
                request.dosageForm(),
                request.manufacturer()));
        return toResponse(medication);

    }

    private MedicationResponse toResponse(Medication medication){
        return new MedicationResponse(
        medication.getId(),
         medication.getGenericName(),
         medication.getBrandName(),
         medication.getStrength(),
         medication.getStrengthUnit(),
         medication.getDosageForm(),
         medication.getManufacturer(),
         medication.isActive(),
         medication.getCreatedAt(),
         medication.getUpdatedAt());
    }



}
