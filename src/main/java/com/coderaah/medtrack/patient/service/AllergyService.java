package com.coderaah.medtrack.patient.service;

import com.coderaah.medtrack.patient.domain.PatientAllergy;
import com.coderaah.medtrack.patient.domain.PatientProfile;
import com.coderaah.medtrack.patient.dto.AllergyRequest;
import com.coderaah.medtrack.patient.dto.AllergyResponse;
import com.coderaah.medtrack.patient.exception.PatientNotFoundException;
import com.coderaah.medtrack.patient.repository.PatientAllergyRepository;
import com.coderaah.medtrack.patient.repository.PatientProfileRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AllergyService {
    private final PatientAllergyRepository allergyRepository;
    private final PatientProfileRepository patientRepository;

    public AllergyService( PatientAllergyRepository allergyRepository, PatientProfileRepository patientRepository) {
        this.allergyRepository = allergyRepository;
        this.patientRepository = patientRepository;
    }

    @Transactional
    public AllergyResponse createAllergy(Long patientId, AllergyRequest request) {
        PatientProfile profile = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(patientId));
        PatientAllergy allergy = new PatientAllergy(
                profile,
                request.allergen(),
                request.severity(),
                request.status()
        );
        allergy.setReaction(request.reaction());
        allergy.setRecordedAt(LocalDateTime.now());
        PatientAllergy saved = allergyRepository.save(allergy);
        return toResponse(saved);

    }

private AllergyResponse toResponse(PatientAllergy allergy) {
    return new AllergyResponse(
            allergy.getId(),
            allergy.getAllergen(),
            allergy.getReaction(),
            allergy.getSeverity(),
            allergy.getStatus(),
            allergy.getRecordedAt());
        }

    }


