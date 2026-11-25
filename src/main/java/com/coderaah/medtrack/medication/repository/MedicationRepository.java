package com.coderaah.medtrack.medication.repository;

import com.coderaah.medtrack.medication.domain.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication,Long> {



}
