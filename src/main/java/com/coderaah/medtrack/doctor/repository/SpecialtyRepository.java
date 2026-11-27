package com.coderaah.medtrack.doctor.repository;

import com.coderaah.medtrack.doctor.domain.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    boolean existsByCode(String code);
    boolean existsByName(String name);
}
