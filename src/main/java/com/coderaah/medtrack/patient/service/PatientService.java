package com.coderaah.medtrack.patient.service;

import com.coderaah.medtrack.identity.domain.Person;
import com.coderaah.medtrack.identity.repository.PersonRepository;
import com.coderaah.medtrack.patient.domain.PatientProfile;
import com.coderaah.medtrack.patient.dto.PatientRequest;
import com.coderaah.medtrack.patient.dto.PatientResponse;
import com.coderaah.medtrack.patient.exception.PatientNotFoundException;
import com.coderaah.medtrack.patient.repository.PatientProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientProfileRepository patientRepository;
    private final PersonRepository personRepository;

    public PatientService(PatientProfileRepository patientRepository, PersonRepository personRepository) {
        this.patientRepository = patientRepository;
        this.personRepository = personRepository;
    }

    @Transactional
    public PatientResponse createPatient(PatientRequest request) {
        Person person = personRepository.save(
                new Person(request.firstName(), request.lastName(), request.birthDate(), request.phone())
        );

        String mrn = "MRN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        PatientProfile profile = new PatientProfile(
                person,
                mrn,
                request.bloodType(),
                request.insuranceProvider(),
                request.insuranceNumber()
        );

        PatientProfile saved = patientRepository.save(profile);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public PatientResponse getPatientById(Long id) {
        PatientProfile profile = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));
        return toResponse(profile);
    }

    @Transactional(readOnly = true)
    public List<PatientResponse> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public PatientResponse updatePatient(Long id, PatientRequest request) {
        PatientProfile profile = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));

        Person person = profile.getPerson();
        person.setFirstName(request.firstName());
        person.setLastName(request.lastName());
        person.setBirthDate(request.birthDate());
        person.setPhone(request.phone());

        profile.setBloodType(request.bloodType());
        profile.setInsuranceProvider(request.insuranceProvider());
        profile.setInsuranceNumber(request.insuranceNumber());

        PatientProfile updated = patientRepository.save(profile);
        return toResponse(updated);
    }

    private PatientResponse toResponse(PatientProfile profile) {
        Person p = profile.getPerson();
        return new PatientResponse(
                profile.getId(),
                p.getId(),
                p.getFirstName(),
                p.getLastName(),
                p.getBirthDate(),
                p.getPhone(),
                profile.getMedicalRecordNumber(),
                profile.getBloodType(),
                profile.getInsuranceProvider(),
                profile.getInsuranceNumber()
        );
    }}