package com.coderaah.medtrack.patient.repository;

import com.coderaah.medtrack.patient.domain.PatientAllergy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientAllergyRepository extends JpaRepository<PatientAllergy,Long> {
}
