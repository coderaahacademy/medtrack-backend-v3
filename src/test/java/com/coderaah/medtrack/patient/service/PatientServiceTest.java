package com.coderaah.medtrack.patient.service;

import com.coderaah.medtrack.identity.domain.Person;
import com.coderaah.medtrack.identity.repository.PersonRepository;
import com.coderaah.medtrack.patient.domain.BloodType;
import com.coderaah.medtrack.patient.domain.PatientProfile;
import com.coderaah.medtrack.patient.dto.PatientRequest;
import com.coderaah.medtrack.patient.dto.PatientResponse;
import com.coderaah.medtrack.patient.exception.PatientNotFoundException;
import com.coderaah.medtrack.patient.repository.PatientProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    PatientProfileRepository patientRepository;

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PatientService patientService;

    private final PatientRequest req = new PatientRequest("First", "Last", LocalDate.of(2000, 1, 1), "123", BloodType.O_POSITIVE, "Prov", "Num");

    @Test
    void createAndList_success() {
        Person p = new Person("First", "Last", LocalDate.of(2000, 1, 1), "123");
        PatientProfile prof = new PatientProfile(p, "MRN-1", BloodType.O_POSITIVE, "Prov", "Num");
        when(personRepository.save(any())).thenReturn(p);
        when(patientRepository.save(any())).thenReturn(prof);
        when(patientRepository.findAll()).thenReturn(List.of(prof));

        assertEquals("First", patientService.createPatient(req).firstName());
        assertEquals(1, patientService.getAllPatients().size());
    }

    @Test
    void getAndUpdate_success() {
        Person p = new Person("First", "Last", LocalDate.of(2000, 1, 1), "123");
        PatientProfile prof = new PatientProfile(p, "MRN-1", BloodType.O_POSITIVE, "Prov", "Num");
        when(patientRepository.findById(1L)).thenReturn(Optional.of(prof));
        when(patientRepository.save(any())).thenReturn(prof);

        assertEquals("First", patientService.getPatientById(1L).firstName());
        assertEquals("First", patientService.updatePatient(1L, req).firstName());
    }

    @Test
    void getAndUpdate_notFound() {
        when(patientRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(PatientNotFoundException.class, () -> patientService.getPatientById(99L));
        assertThrows(PatientNotFoundException.class, () -> patientService.updatePatient(99L, req));
    }}