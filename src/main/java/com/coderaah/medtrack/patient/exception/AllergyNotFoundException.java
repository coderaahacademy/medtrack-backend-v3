package com.coderaah.medtrack.patient.exception;

public class AllergyNotFoundException extends RuntimeException {
    public AllergyNotFoundException(Long id){
        super("Allergy not found with id: " + id);
    }

}
