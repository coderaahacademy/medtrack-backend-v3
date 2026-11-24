package com.coderaah.medtrack.identity.repository;

import com.coderaah.medtrack.identity.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByFirstNameAndLastNameAndBirthDate(String firstName, String lastName, LocalDate birthDate);
}