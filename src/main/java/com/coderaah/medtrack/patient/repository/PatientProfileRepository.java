package com.coderaah.medtrack.patient.repository;

import com.coderaah.medtrack.patient.domain.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientProfileRepository extends JpaRepository<PatientProfile, Long> {
    Optional<PatientProfile> findByPersonId(Long personId);
    boolean existsByMedicalRecordNumber(String medicalRecordNumber);
}