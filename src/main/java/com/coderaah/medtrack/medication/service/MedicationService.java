package com.coderaah.medtrack.medication.service;

import com.coderaah.medtrack.medication.domain.Medication;
import com.coderaah.medtrack.medication.dto.MedicationRequest;
import com.coderaah.medtrack.medication.dto.MedicationResponse;
import com.coderaah.medtrack.medication.repository.MedicationRepository;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.util.List;

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

    @Transactional(readOnly = true)
    public List<MedicationResponse> getAllMedications(){
        return medicationRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public MedicationResponse getMedicationById (long id){
        Medication medication = medicationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"medication not found"));
        return toResponse(medication);
    }

    @Transactional
    public MedicationResponse updateMedication(Long id, MedicationRequest medicationRequest){
        Medication medication = medicationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"medication not found"));

        medication.setBrandName(medicationRequest.brandName());
        medication.setGenericName(medicationRequest.genericName());
        medication.setDosageForm(medicationRequest.dosageForm());
        medication.setManufacturer(medicationRequest.manufacturer());
        medication.setStrength(medicationRequest.strength());
        medication.setManufacturer(medicationRequest.strengthUnit());

        Medication updatedMedication = medicationRepository.save(medication);
        return toResponse(updatedMedication);
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
